package com.linkedin.collections;

import java.util.*;

public class RoomService {

	private Collection<Room> inventory;

	public RoomService() {
		this.inventory = new LinkedHashSet<>();
	}
	
	public boolean hasRoom(Room room) {
		
	// 1. Returns a boolean that indicates if the Room Inventory contains a Room.
		
		return inventory.contains(room);
	}
	
	public Room[] asArray() {
		
	// 2. Returns all Rooms as an Array of Rooms in the **order** they were Added.
		
		Room[] array = new Room[inventory.size()];
		int i = 0;
		for (Room room : inventory) {
			array[i++] = room;
		}
		return array;
	}
	
	public Collection<Room> getByType(String type){

	/*
	   3. Return a new Collection of Rooms where Room#type matches the provided String.
		  The original Room Inventory collection MUST NOT BE MODIFIED.
	*/
		
		Collection<Room> matching = new ArrayList<>();
		inventory.forEach(room -> {
					boolean isSameType = room.getType().equals(type);
					if (isSameType) {
						matching.add(room);
					}
				});
		return matching;
	}

	public Collection<Room> getInventory() {
		return new HashSet<>(this.inventory); 
	}

	public void createRoom(String name, String type, int capacity, double price) {
		this.inventory.add(new Room(name, type, capacity, price));
	}

	public void createRooms(Room[] rooms) {
		this.inventory.addAll(Arrays.asList(rooms));
	}

	public void removeRoom(Room room) {
		this.inventory.remove(room);
	}

}
