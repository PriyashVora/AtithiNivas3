package com.atithinivas.hotel.roomservice.service.implementation;

import com.atithinivas.hotel.roomservice.dto.HotelDTO;
import com.atithinivas.hotel.roomservice.dto.HotelWithRoomsDTO; // NEW IMPORT
import com.atithinivas.hotel.roomservice.dto.RoomDTO; // NEW IMPORT
import com.atithinivas.hotel.roomservice.dto.SearchRequestDTO; // NEW IMPORT
import com.atithinivas.hotel.roomservice.entity.Hotel;
import com.atithinivas.hotel.roomservice.entity.Room; // NEW IMPORT
import com.atithinivas.hotel.roomservice.repository.HotelRepository;
import com.atithinivas.hotel.roomservice.repository.RoomRepository; // NEW IMPORT
import com.atithinivas.hotel.roomservice.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository; // NEW DEPENDENCY

    // Constructor injection is the preferred way for dependencies
    // UPDATED Constructor to include RoomRepository
    public HotelServiceImpl(HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    // --- Private Mapping Methods ---

    /** Converts HotelDTO to Hotel Entity. Assumes default user if managerName is not provided. */
    private Hotel convertToEntity(HotelDTO dto) {
        // NOTE: createdBy/updatedBy are being populated from the DTO's managerName field.
        String user = dto.getManagerName() != null && !dto.getManagerName()
                .isEmpty() ? dto.getManagerName() : "ADMIN_DEFAULT";
        return Hotel.builder()
                .hotelId(dto.getHotelId())
                .name(dto.getName())
                .location(dto.getLocation())
                .amenities(dto.getAmenities())
                .rating(dto.getRating())
                .createdBy(user)
                .updatedBy(user)
                .build();
    }

    /** Converts Hotel Entity to HotelDTO. Assumes createdBy is the manager's name for the DTO. */
    private HotelDTO convertToDTO(Hotel entity) {
        // NOTE: managerName is populated from the Entity's createdBy field.
        return HotelDTO.builder()
                .hotelId(entity.getHotelId())
                .name(entity.getName())
                .location(entity.getLocation())
                .amenities(entity.getAmenities())
                .rating(entity.getRating())
                .managerName(entity.getCreatedBy()) // Assuming createdBy is the manager
                .build();
    }

    //  Maps Room Entity to Room DTO
    private RoomDTO convertRoomToDTO(Room entity) {
        return RoomDTO.builder()
                .roomId(entity.getRoomId())
                .hotelId(entity.getHotelId())
                .availability(entity.getAvailability())
                .type(entity.getType())
                .price(entity.getPrice())
                .features(entity.getFeatures())
                .managerName(entity.getCreatedBy())
                .build();
    }


    //  Search Implementation ---
    @Override
    public List<HotelWithRoomsDTO> searchHotels(SearchRequestDTO request) {
        // 1. Search Hotels by Location (City parameter from DTO)
        List<Hotel> matchingHotels = hotelRepository.findByLocation(request.getCity());

        // 2. Filter and Map Results
        return matchingHotels.stream()
                .map(hotel -> {
                    // Convert Hotel Entity details into the composite DTO
                    HotelWithRoomsDTO dto = HotelWithRoomsDTO.builder()
                            .hotelId(hotel.getHotelId())
                            .name(hotel.getName())
                            .location(hotel.getLocation())
                            .rating(hotel.getRating())
                            .amenities(hotel.getAmenities())
                            .build();

                    // 3. Find available rooms for this hotel
                    // SIMPLIFIED LOGIC: Find all rooms associated with the hotelId that are marked 'available=true'.
                    List<Room> availableRooms = roomRepository.findByHotelId(hotel.getHotelId()).stream()
                            .filter(Room::getAvailability)
                            .collect(Collectors.toList());

                    // Convert Room Entities to RoomDTOs
                    List<RoomDTO> roomDTOs = availableRooms.stream()
                            .map(this::convertRoomToDTO)
                            .collect(Collectors.toList());

                    dto.setAvailableRooms(roomDTOs);
                    return dto;
                })
                .filter(dto -> !dto.getAvailableRooms().isEmpty()) // Only return hotels that have available rooms
                .collect(Collectors.toList());
    }

    // --- Existing CRUD Implementations ---

    @Override
    public HotelDTO createHotel(HotelDTO request) {
        Hotel hotelEntity = convertToEntity(request);

        // Generate a UUID for the public hotelId if it's a new entity
        if (hotelEntity.getHotelId() == null || hotelEntity.getHotelId().isEmpty()) {
            hotelEntity.setHotelId(UUID.randomUUID().toString());
        }

        Hotel saved = hotelRepository.save(hotelEntity);
        return convertToDTO(saved);
    }

    @Override
    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HotelDTO getHotelByHotelId(String hotelId) {
        Hotel hotel = hotelRepository.findHotelByHotelId(hotelId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found with ID: " + hotelId));
        return convertToDTO(hotel);
    }

    @Override 
    public HotelDTO updateHotel(Long id, Hotel hotelDetails) {
        Hotel existingHotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found with ID: " + id));

        // Update mutable business fields from the request (hotelDetails)
        // Note: The controller sends a full Hotel entity in the request body for PUT.
        existingHotel.setName(hotelDetails.getName());
        existingHotel.setLocation(hotelDetails.getLocation());
        existingHotel.setAmenities(hotelDetails.getAmenities());
        existingHotel.setRating(hotelDetails.getRating());

        // Set the updatedBy field
        if (hotelDetails.getUpdatedBy() != null && !hotelDetails.getUpdatedBy().isEmpty()) {
            existingHotel.setUpdatedBy(hotelDetails.getUpdatedBy());
        } else {
            // Fallback to the original creator or default if updatedBy is not supplied
            existingHotel.setUpdatedBy(existingHotel.getCreatedBy() != null ? existingHotel.getCreatedBy() : "ADMIN_DEFAULT");
        }

        // PreUpdate will handle updatedOn
        Hotel updated = hotelRepository.save(existingHotel);
        return convertToDTO(updated);
    }

    @Override
    public void deleteHotel(Long id) {
        if (!hotelRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found with ID: " + id);
        }
        hotelRepository.deleteById(id);
    }

    // --- Custom Queries Implementations (using new repository methods) ---

    @Override
    public List<HotelDTO> getHotelsByLocation(String location) {
        return hotelRepository.findByLocation(location).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HotelDTO> getHotelsByRating(String rating) {
        return hotelRepository.findByRating(rating).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public HotelRepository getHotelRepository() {
        return hotelRepository;
    }
}