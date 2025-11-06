package com.atithinivas.hotel.roomservice.service.implementation;

import com.atithinivas.hotel.roomservice.dto.RoomDTO;
import com.atithinivas.hotel.roomservice.entity.Room;
import com.atithinivas.hotel.roomservice.repository.HotelRepository;
import com.atithinivas.hotel.roomservice.repository.RoomRepository;
import com.atithinivas.hotel.roomservice.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    // Constructor injection for dependencies
    public RoomServiceImpl(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    // --- Private Mapping Methods ---

    private Room convertToEntity(RoomDTO dto) {
        String user = dto.getManagerName() != null && !dto.getManagerName().isEmpty() ? dto.getManagerName() : "ADMIN_DEFAULT";
        return Room.builder()
                .roomId(dto.getRoomId())
                .hotelId(dto.getHotelId())
                .availability(dto.getAvailability())
                .type(dto.getType())
                .price(dto.getPrice())
                .features(dto.getFeatures())
                .createdBy(user)
                .updatedBy(user)
                .build();
    }

    private RoomDTO convertToDTO(Room entity) {
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

    // --- CRUD Implementations ---

    @Override
    public RoomDTO createRoom(String hotelId, RoomDTO request) {
        // NEGATIVE FLOW: Check if the Hotel exists (404 Not Found scenario)
        if (!hotelRepository.existsByHotelId(hotelId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found with ID: " + hotelId);
        }

        // Ensure the room is associated with the hotelId from the path
        request.setHotelId(hotelId);

        Room roomEntity = convertToEntity(request);

        // Generate a UUID for the public roomId if it's new
        if (roomEntity.getRoomId() == null || roomEntity.getRoomId().isEmpty()) {
            roomEntity.setRoomId(UUID.randomUUID().toString());
        }

        Room saved = roomRepository.save(roomEntity);
        return convertToDTO(saved);
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDTO getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found with ID: " + id));
        return convertToDTO(room);
    }

    @Override
    public RoomDTO updateRoom(Long id, Room roomDetails) {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found with ID: " + id));

        // Update mutable business fields
        existingRoom.setHotelId(roomDetails.getHotelId());
        existingRoom.setAvailability(roomDetails.getAvailability());
        existingRoom.setType(roomDetails.getType());
        existingRoom.setPrice(roomDetails.getPrice());
        existingRoom.setFeatures(roomDetails.getFeatures());

        // Set the updatedBy field
        if (roomDetails.getUpdatedBy() != null && !roomDetails.getUpdatedBy().isEmpty()) {
            existingRoom.setUpdatedBy(roomDetails.getUpdatedBy());
        } else {
            existingRoom.setUpdatedBy(existingRoom.getCreatedBy() != null ? existingRoom.getCreatedBy() : "ADMIN_DEFAULT");
        }

        Room updated = roomRepository.save(existingRoom);
        return convertToDTO(updated);
    }

    @Override
    public void deleteRoom(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found with ID: " + id);
        }
        roomRepository.deleteById(id);
    }

    // --- Custom Queries Implementations ---

    @Override
    public List<RoomDTO> getRoomsByHotelId(String hotelId) {
        return roomRepository.findByHotelId(hotelId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDTO> getRoomsByAvailability(Boolean availability) {
        return roomRepository.findByAvailability(availability).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}