import React, { useContext, useEffect, useState } from "react";
import { AuthContext } from "../../contextApi/AuthContext";
 
const API_BASE = "http://localhost:3000";
 
export default function Loyalty() {
  const { user } = useContext(AuthContext);
  const userId = user?.user_id;
 
  const [account, setAccount] = useState(null);
  const [history, setHistory] = useState([]);
  const [loading, setLoading] = useState(true);
  const [message, setMessage] = useState(null);
 
  useEffect(() => {
    if (!userId) return;
 
    async function loadData() {
      try {
        setLoading(true);
 
        const [resLoyalty, resRedemptions] = await Promise.all([
          fetch(`${API_BASE}/api/v1/loyalty/${userId}`),
          fetch(`${API_BASE}/api/v1/redemptions/${userId}`),
        ]);
 
        if (resLoyalty.ok) {
          const data = await resLoyalty.json();
          setAccount(data[0]);
        } else {
          setMessage({ type: "error", text: "Failed to load loyalty details." });
        }
 
        if (resRedemptions.ok) {
          const data = await resRedemptions.json();
          setHistory(data);
        }
      } catch (err) {
        console.error("Failed to load loyalty data:", err);
        setMessage({ type: "error", text: "Error loading data." });
      } finally {
        setLoading(false);
      }
    }
 
    loadData();
  }, [userId]);
 
  if (loading) {
    return (
      <div className="w-full pb-6">
        <div className="bg-white shadow rounded-md p-6 text-center text-gray-600">
          Loading loyalty details…
        </div>
      </div>
    );
  }
 
  if (!account) {
    return (
      <div className="w-full pb-6">
        <div className="bg-white shadow rounded-md p-6 text-center text-gray-500">
          No loyalty account found.
        </div>
      </div>
    );
  }
 
  return (
    <div className="w-full pb-6">
      <div className="bg-white shadow rounded-md p-4">
        <h2 className="text-xl font-semibold mb-4">Loyalty Points</h2>
 
        {/* Points Summary */}
        <div className="bg-white border border-gray-200 rounded-lg p-5 w-full sm:w-96 shadow-sm mb-6">
          <div className="text-gray-500 font-bold mb-2">Total Points</div>
          <div className="text-4xl font-extrabold text-gray-900 mb-1">
            {account.pointsbalance.toLocaleString()}
          </div>
          <div className="text-sm text-gray-500">
            Last updated:{" "}
            {new Date(account.lastupdated).toLocaleDateString("en-IN")}
          </div>
        </div>
 
        {/* Redemption History */}
        <h3 className="text-lg font-semibold mb-3">Redemption History</h3>
        {history.length === 0 ? (
          <div className="p-4 text-gray-500 font-medium text-center bg-gray-50 rounded-md">
            No redemptions yet.
          </div>
        ) : (
          <div className="flex flex-col gap-3">
            {history.map((r) => (
              <div
                key={r.redemption_id}
                className="flex justify-between items-center bg-white border border-gray-200 rounded-lg shadow-sm px-4 py-3"
              >
                <div>
                  <div className="text-gray-900 font-bold text-base">
                    -{r.pointsused} pts
                  </div>
                  <div className="text-gray-500 text-sm">
                    ₹{Number(r.discountamount).toFixed(2)} discount
                  </div>
                </div>
                <div className="text-sm text-gray-500">
                  Booking: {r.booking_id}
                </div>
              </div>
            ))}
          </div>
        )}
 
        {/* Message Display */}
        {message && (
          <div
            className={`mt-4 px-3 py-2 rounded-md text-sm font-semibold ${
              message.type === "error"
                ? "bg-red-50 text-red-700 border border-red-200"
                : "bg-green-50 text-green-700 border border-green-200"
            }`}
          >
            {message.text}
          </div>
        )}
      </div>
    </div>
  );
}