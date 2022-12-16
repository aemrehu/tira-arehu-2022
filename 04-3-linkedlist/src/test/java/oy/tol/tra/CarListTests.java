package oy.tol.tra;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@Timeout(60)
@DisplayName("Testing the ListImplementation class with Car objects.")
public class CarListTests {

	static int index = -1;
	static boolean result = false;
	static Car car = null;
	static final String correctListAsString = "[GGG-555 2022, GGG-666 2022, GGG-777 2022]";

	@Test
	void testAddingCarObjects() {
		LinkedListImplementation<Car> carList = new LinkedListImplementation<>();
		final int size = addCarObjectsToList(carList);
		int lastElementIndex = size - 1;
		assertEquals(size, carList.size(), () -> "list size should be " + size);
		assertDoesNotThrow(() -> index = carList.indexOf(new Car("AAA-111", 0)), "indexOf must not throw");
		assertEquals(0, index, () -> "Car AAA-111 should be in the list at the index 0");
		assertDoesNotThrow(() -> index = carList.indexOf(new Car("GGG-888", 0)), "indexOf must not throw");
		assertEquals(lastElementIndex, index, () -> "Car GGG-888 should be in the list at the index: " + index);
		assertDoesNotThrow(() -> carList.add(0, new Car("313", 1954)), "Adding to beginning of list must not throw");
		assertDoesNotThrow(() -> index = carList.indexOf(new Car("313", 0)), "indexOf must not throw");
		assertEquals(0, index, () -> "Car 313 should be in the list at the index 0");
	}

	@Test
	void addingAndRemovingCarObjects() {
		LinkedListImplementation<Car> carList = new LinkedListImplementation<>();
		final int size = addCarObjectsToList(carList);
		int lastElementIndex = size - 1;
		assertEquals(size, carList.size(), () -> "list size should be " + size);
		assertDoesNotThrow(() -> index = carList.indexOf(new Car("BBB-222", 0)), "indexOf must not throw");
		assertEquals(9, index, () -> "Car BBB-222 should be in the list at the index 9");

		assertDoesNotThrow(() -> result = carList.remove(new Car("BBB-222", 0)), "remove(T element) must not throw");
		assertTrue(result, "Removing car BBB-222 must return true since it is in the list");
		assertEquals(size - 1, carList.size(), () -> "Car list must have one less element after removal, but size was the same");
		lastElementIndex--;

		Car ggg = new Car("GGG-888", 0);
		assertDoesNotThrow(() -> index = carList.indexOf(ggg), "indexOf must not throw");
		assertEquals(lastElementIndex, index, () -> "Car GGG-888 should be in the list at the index: " + index);
		assertDoesNotThrow(() -> car = carList.remove(index), "Must not throw when calling remove(int index)");
		assertEquals(size - 2, carList.size(), () -> "Car list must have one less element after removal, but size was the same");
		assertEquals(ggg, car, () -> "Search element must be the one removed from the index it was found in.");
		while (carList.size() > 3) {
			assertDoesNotThrow(() -> car = carList.remove(0), "Must not throw when removing from first index");
			assertNotNull(car, "Car returned from remove(int index) must not be null");
		}
		assertEquals(correctListAsString, carList.toString());
	}

	private int addCarObjectsToList(LinkedListInterface<Car> list) {
		String [] regNumLetters = { "AAA", "BBB", "CCC", "DDD", "EEE", "FFF", "GGG" };
		Integer [] regNumNumbers = { 111, 222, 333, 444, 555, 666, 777, 888 };

		int listSize = 0;
		for (int index = 0; index < regNumLetters.length; index++) {
			for (int index2 = 0; index2 < regNumNumbers.length; index2++) {
				final Integer number = regNumNumbers[index2];
				final String regNumLetter = regNumLetters[index];
				final String wholeRegNum = regNumLetter + "-" + Integer.toString(number);
				assertDoesNotThrow(() -> list.add(new Car(wholeRegNum, 2022)), "Adding to a linked list must not throw");
				listSize++;
			}
		}
		return listSize;
	}

}
