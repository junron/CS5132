package pa2;

import java.util.Comparator;

public class ItemSort implements Comparator<Item> {

  @Override
  public int compare(Item item1, Item item2) {
    // Order in descending order
    return item2.getValue() - item1.getValue();
  }
}
