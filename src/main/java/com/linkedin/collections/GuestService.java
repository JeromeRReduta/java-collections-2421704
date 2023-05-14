package com.linkedin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GuestService {

	private List<Guest> checkinList = new ArrayList<>(100);

	public static List<Guest> filterByFavoriteRoom(List<Guest> guests, Room room) {

		/*
		 *  1. Returns a new collection that contains guests from the provided collection
		 *  who have indicated the provided room as the first preference in their preferred
		 *  room list. 
		 */
		return guests.stream()
				.filter(guest -> {
					List<Room> preferredRooms = guest.getPreferredRooms();
					// If guest has no preferred rooms, return false immediately
					if (preferredRooms == null || preferredRooms.isEmpty()) {
						return false;
					}
					// Else, check if their first preference == room
					return preferredRooms.get(0).equals(room);
				})
				.collect(Collectors.toList());
	}

	public void checkIn(Guest guest) {
		
		/*
		 *  2. Adds a guest to the checkinList, placing members of the loyalty program
		 *  ahead of those guests not in the program. Otherwise, guests are arranged in the
		 *  order they were inserted.
		 */
		if (guest.isLoyaltyProgramMember()) {
			checkInLoyaltyProgramMember(guest);
		}
		else {
			checkInRegularMember(guest);
		}

	}

	private void checkInLoyaltyProgramMember(Guest guest) {
		/* Find first index of non-loyalty program guest */
		int i = 0;
		for (Guest current : this.checkinList) {
			System.out.println(current + " " + current.isLoyaltyProgramMember());
			if (!current.isLoyaltyProgramMember()) {
				break;
			}
			i++;
		}
		/* Add guest there, shifting everything after to the right */
		System.out.println(i);
		this.checkinList.add(i, guest);
	}

	private void checkInRegularMember(Guest guest) {
		this.checkinList.add(guest);
	}
	
	public void swapPosition(Guest guest1, Guest guest2) {
		
		/*
		 *  3.  Swaps the position of the two provided guests within the checkinList.
		 *  If guests are not currently in the list no action is required.
		 */
		int guest1Index = this.checkinList.indexOf(guest1);
		int guest2Index = this.checkinList.indexOf(guest2);
		/* If at least one guest isn't present, return */
		if (guest1Index == -1 || guest2Index == -1) {
			return;
		}
		/* Save guest2 to temp before overwriting */
		Guest temp = guest2;
		/* Put guest1 where guest2 is, then put temp where guest1 is */
		this.checkinList.set(guest2Index, guest1);
		this.checkinList.set(guest1Index, temp);
	}

	public List<Guest> getCheckInList() {
		return List.copyOf(this.checkinList);
	}
}
