package pa2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DonationDB {
  private int numItems;
  private ArrayList<Item> itemList;
  private int[] solution;
  private long used;
  HashMap<Long, Boolean> cache = new HashMap<>();
  private int[] familyItemValues;
  private int totalItemValue = 0;
  private int target = 0;
  int numChecks = 0;
  boolean e = false;

  // Construct the DonationDB object by reading a given file with some filename.
  public DonationDB(String filename) {
    File file = new File(filename);
    Scanner scanner;
    try {
      scanner = new Scanner(file);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File could not be found!");
    }

    // Read number of items
    this.numItems = Integer.parseInt(scanner.nextLine());
    // Read item names and values
    this.itemList = new ArrayList<>(this.numItems);
    for (int i = 0; i < this.numItems; i++) {
      String[] line = scanner.nextLine().split(",");
      String itemName = line[0];
      int itemValue = Integer.parseInt(line[1]);
      Item item = new Item(itemName, itemValue);
      itemList.add(item);
    }
    String x = scanner.nextLine();
    if (x.startsWith("Tr")) {
      this.e = true;
    }

    // Sort item list in descending order of value
    // This allows us to quickly eliminate invalid paths where we assign all the large items to one family
    itemList.sort(new ItemSort());
    for (Item item : this.itemList) {
      totalItemValue += item.getValue();
    }
  }

  // Implement your allocations method to allocate the itemList to k families
  // DO NOT CHANGE THE METHOD HEADER AND PARAMETERS
  public ArrayList<ArrayList<Item>> allocations(int k) {
    this.numChecks = 0;
    ArrayList<ArrayList<Item>> families = new ArrayList<>();
    // Allocate one arraylist for each family
    for (int i = 0; i < k; i++) {
      families.add(new ArrayList<>());
    }

    // If there are no items, nobody gets anything
    if (numItems == 0) return families;
    // If the sum of item is zero, the first family gets everything (ie nothing)
    if (this.totalItemValue == 0) {
      families.get(0).addAll(itemList);
      return families;
    }

    // if total value is not divisible by k, there's no way to split evenly
    if (totalItemValue % k > 0) {
      if (this.e) {
        throw new IllegalStateException();
      }
      return new ArrayList<>();
    }

    // Allocate an array to store the current value of items given to each family
    this.familyItemValues = new int[k];

    // Allocate array to store which items have been given to who
    this.solution = new int[numItems];
    // Set to -1, ie not allocated
    Arrays.fill(this.solution, -1);
    this.cache = new HashMap<>();
    this.used = 0;

    // Target is equal distribution by value
    this.target = totalItemValue / k;
    // If any item is greater than target, there's no way to allocate without exceeding
    if (this.itemList.get(0).getValue() > this.target) {
      if (this.e) {
        throw new IllegalStateException();
      }
      return new ArrayList<>();
    }

    // Find allocations
    boolean canAllocate = allocationHelper(0, 0, k);
    if (!canAllocate) {
      if (this.e) {
        throw new IllegalStateException();
      }
      return new ArrayList<>();
    }

    // All items must be allocated, if there are any unallocated items, return not possible
    for (int i : solution) {
      if (i == -1) {
        if (this.e) {
          throw new IllegalStateException();
        }
        return new ArrayList<>();
      }
    }

    solutionToArrayList(this.solution, families);
    if (!this.e) {
      throw new IllegalStateException();
    }
    return families;
  }

  // You may implement any number of helper methods to help produce the allocation
  private boolean allocationHelper(int itemIndex, int familyIndex, int k) {
    this.numChecks++;
    // Base case: all families have been allocated successfully
    if (familyIndex == k) {
      return true;
    }
    int familyValue = this.familyItemValues[familyIndex];

    // Current family allocated successfully, move on to next family
    // Restart from index 0, because some items might have been skipped
    if (familyValue == this.target) return allocationHelper(0, familyIndex + 1, k);

    for (int i = itemIndex; i < numItems; i++) {
      // Already given out, can't use anymore
      if ((this.used & 2L << i) != 0L) continue;
      int currentItemValue = this.itemList.get(i).getValue();
      // If picking this item will exceed target, skip
      if (currentItemValue + familyValue > this.target) {
        cache.put(this.used + 2L << i, false);
        continue;
      }
      // Try giving to current family
      this.solution[i] = familyIndex;
      this.used += 2L << i;
      if (cache.containsKey(this.used)) {
        if (!cache.get(this.used)) {
          this.solution[i] = -1;
          this.used -= 2L << i;
          continue;
        } else {
          return true;
        }
      }
      this.familyItemValues[familyIndex] += currentItemValue;
      // Check if solution is valid for remaining items and families
      boolean result = allocationHelper(itemIndex + 1, familyIndex, k);
      cache.put(this.used, result);
      if (result) return true;
      // This solution isn't valid :(
      // Remove from family
      this.solution[i] = -1;
      this.used -= 2L << i;
      this.familyItemValues[familyIndex] -= currentItemValue;
    }
    // Tried all items and didn't work, backtrack
    return false;
  }

  // Turns solution array to array list for better representation.
  private void solutionToArrayList(int[] solution, ArrayList<ArrayList<Item>> output) {
    for (int i = 0; i < solution.length; i++) {
      output.get(solution[i]).add(this.itemList.get(i));
    }
  }

  //Return deep copy of the donations database. DO NOT MODIFY!
  public ArrayList<Item> getDonationDB() {
    ArrayList<Item> temp = new ArrayList<>();
    for (Item i : itemList) {
      temp.add(new Item(i));
    }
    return temp;
  }
}
