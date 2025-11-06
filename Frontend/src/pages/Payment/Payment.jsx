
import React, { useState, useEffect, useContext } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { AuthContext } from "../../contextApi/AuthContext";
import toast from "react-hot-toast";
import { offers } from "../../data/dummyData";

const API_BASE = "http://localhost:3000/api/v1";

export default function Payment() {
    const { bookingId } = useParams();
    const { user } = useContext(AuthContext);
    const navigate = useNavigate();

    const [booking, setBooking] = useState(null);
    const [room, setRoom] = useState(null);
    const [loyalty, setLoyalty] = useState(null);
    const [redeemAmt, setRedeemAmt] = useState("");
    const [loyaltyDiscount, setLoyaltyDiscount] = useState(0);
    const [processing, setProcessing] = useState(false);
    const [loading, setLoading] = useState(true);
    const [selectedOffer, setSelectedOffer] = useState(null);
    const [offerDiscount, setOfferDiscount] = useState(0);

    useEffect(() => {
        async function loadData() {
            try {
                if (!bookingId) throw new Error("Booking ID is missing.");
                
                const bookingRes = await fetch(`${API_BASE}/bookings/${bookingId}`);
                if (!bookingRes.ok) throw new Error("Could not find your booking details.");
                const bookingData = await bookingRes.json();
                setBooking(bookingData);

                const roomRes = await fetch(`${API_BASE}/rooms/${bookingData.room_id}`);
                if (!roomRes.ok) throw new Error("Could not find room details.");
                const roomData = await roomRes.json();
                setRoom(roomData);

                const loyaltyRes = await fetch(`${API_BASE}/loyalty/${user.user_id}`);
                if (loyaltyRes.ok) {
                    const loyaltyData = await loyaltyRes.json();
                    setLoyalty(loyaltyData[0]);
                }
            } catch (err) {
                console.error("Failed to load payment data:", err);
                toast.error(err.message);
            } finally {
                setLoading(false);
            }
        }
        loadData();
    }, [bookingId, user.user_id]);

    const calculateTotalDays = () => {
        if (!booking || !booking.checkindate || !booking.checkoutdate) return 1;
        const checkIn = new Date(booking.checkindate);
        const checkOut = new Date(booking.checkoutdate);
        const diffTime = Math.abs(checkOut - checkIn);
        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
        return diffDays > 0 ? diffDays : 1;
    };

    const totalDays = calculateTotalDays();
    const totalAmount = room ? room.price * totalDays : 0;
    const finalAmount = Math.max(totalAmount - loyaltyDiscount - offerDiscount, 0);

    const handleRedeem = async () => {
        const points = parseInt(redeemAmt, 10);
        if (!loyalty || Number.isNaN(points) || points <= 0 || points > loyalty.pointsbalance) {
            toast.error("Please enter a valid amount of points to redeem.");
            return;
        }

        try {
            // Step 1: Update the loyalty balance on the server
            const newBalance = loyalty.pointsbalance - points;
            const patchRes = await fetch(`${API_BASE}/loyalty/${user.user_id}`, {
                method: "PATCH",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    pointsbalance: newBalance,
                    lastupdated: new Date().toISOString().split("T")[0],
                }),
            });

            if (!patchRes.ok) throw new Error("Failed to update loyalty points on the server.");

            // Step 2: Create a new redemption record on the server
            await fetch(`${API_BASE}/redemptions`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    user_id: user.user_id,
                    booking_id: bookingId,
                    pointsused: points,
                    discountamount: points, // Assuming 1 point = ₹1
                }),
            });
            
            // Step 3: Update the local state to reflect the change immediately
            setLoyalty({ ...loyalty, pointsbalance: newBalance });
            setLoyaltyDiscount(points);
            setRedeemAmt("");
            toast.success(`Redeemed ${points} points! ₹${points} discount applied.`);
        } catch (err) {
            console.error("Redemption failed:", err);
            toast.error("Redemption failed. Please try again.");
        }
    };
    
    const handleApplyOffer = (offerId) => {
        const offer = offers.find(o => o.id === parseInt(offerId));
        if (offer && offer.description) {
            const percentageMatch = offer.description.match(/\d+/);
            
            if (percentageMatch && percentageMatch[0]) {
                const percentage = parseInt(percentageMatch[0], 10);
                const discountValue = (totalAmount * percentage) / 100;
                setOfferDiscount(discountValue);
                setSelectedOffer(offer);
                toast.success(`Offer "${offer.title}" applied!`);
            } else {
                 toast.error("This offer does not have a valid discount percentage.");
            }
        } else {
            setOfferDiscount(0);
            setSelectedOffer(null);
        }
    };

    const handlePayment = async () => {
        if (!booking || !room || !user) {
          toast.error("Missing booking, room, or user data.");
          return;
        }
        setProcessing(true);
 
        try {
          const newPayment = {
            user_id: user.user_id,
            bookingid: bookingId,
            amount: finalAmount,
            status: "completed",
            paymentmethod: "Credit Card",
          };
 
          const paymentRes = await fetch(`${API_BASE}/payment`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newPayment),
          });
 
          if (!paymentRes.ok) throw new Error("Payment creation failed.");
          const createdPayment = await paymentRes.json();
 
          await fetch(`${API_BASE}/bookings/${bookingId}`, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
              status: "confirmed",
              payment_id: createdPayment.payment_id,
            }),
          });
 
          toast.success("Payment Successful! Your booking is confirmed.");
          // --- THIS IS THE FIX ---
          // Navigate to the correct user dashboard route
          navigate("/user/bookings");
          // ----------------------
        } catch (err) {
          console.error("Payment failed:", err);
          toast.error("Payment failed: " + err.message);
        } finally {
          setProcessing(false);
        }
    };

    if (loading) return <div className="text-center p-20">Loading payment details...</div>;
    if (!booking || !room) return <div className="text-center p-20">Could not load booking information.</div>;

    return (
        <div className="container mx-auto my-10 p-4 max-w-lg">
            <div className="bg-white p-8 rounded-lg shadow-lg">
                <h2 className="text-3xl font-bold text-center text-amber-700 mb-6">Finalize Payment</h2>

                <div className="bg-gray-100 p-4 rounded-lg mb-6 space-y-2">
                    <h3 className="font-bold text-lg">Booking Summary</h3>
                    <div className="flex justify-between"><span>Subtotal:</span> <span>₹{totalAmount.toLocaleString()}</span></div>
                    {loyaltyDiscount > 0 && (
                        <div className="flex justify-between text-green-700 font-semibold">
                            <span>Loyalty Discount:</span>
                            <span>- ₹{loyaltyDiscount.toLocaleString()}</span>
                        </div>
                    )}
                    {offerDiscount > 0 && (
                        <div className="flex justify-between text-green-700 font-semibold">
                            <span>Offer Discount:</span>
                            <span>- ₹{offerDiscount.toLocaleString(undefined, { minimumFractionDigits: 2 })}</span>
                        </div>
                    )}
                    <hr className="my-2" />
                    <div className="flex justify-between text-xl font-bold">
                        <span>Total Payable:</span>
                        <span>₹{finalAmount.toLocaleString(undefined, { minimumFractionDigits: 2 })}</span>
                    </div>
                </div>

                <div className="mb-6 bg-gray-50 p-4 rounded-lg border">
                    <h3 className="font-bold mb-2">Apply an Offer</h3>
                    <select
                        onChange={(e) => handleApplyOffer(e.target.value)}
                        className="w-full p-3 border border-gray-300 rounded-md text-sm"
                        defaultValue=""
                    >
                        <option value="" >Select an available offer</option>
                        {offers.map(offer => (
                            <option key={offer.id} value={offer.id}>
                                {offer.title}
                            </option>
                        ))}
                    </select>
                </div>

                {loyalty && (
                    <div className="mb-6 bg-gray-50 p-4 rounded-lg border">
                        <h3 className="font-bold mb-2">Redeem Loyalty Points</h3>
                        <p className="text-sm text-gray-600 mb-3">You have <strong>{loyalty.pointsbalance}</strong> points available.</p>
                        <div className="flex gap-3">
                            <input
                                type="number"
                                placeholder="Points to redeem"
                                value={redeemAmt}
                                onChange={(e) => setRedeemAmt(e.target.value)}
                                className="w-40 px-3 py-2 border border-gray-300 rounded-md text-sm"
                            />
                            <button
                                onClick={handleRedeem}
                                className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-md text-sm font-semibold"
                            >
                                Redeem
                            </button>
                        </div>
                    </div>
                )}

                <div className="space-y-4">
                    <input placeholder="Card Number" className="w-full p-3 border rounded-lg" />
                    <div className="flex gap-4">
                        <input placeholder="MM/YY" className="w-1/2 p-3 border rounded-lg" />
                        <input placeholder="CVC" className="w-1/2 p-3 border rounded-lg" />
                    </div>
                </div>

                <button
                    onClick={handlePayment}
                    disabled={processing}
                    className="w-full mt-6 bg-amber-600 text-white py-3 rounded-lg font-semibold hover:bg-amber-700 transition disabled:bg-gray-400"
                >
                    {processing ? "Processing..." : `Pay ₹${finalAmount.toLocaleString()} Now`}
                </button>
            </div>
        </div>
    );
}

