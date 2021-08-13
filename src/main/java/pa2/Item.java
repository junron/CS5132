package pa2;

// DO NOT EDIT THE CLASS!
// Item object storing information for each donated item
public class Item {
    private String name;
    private int value;

    public Item(String name, int value){
        this.name = name;
        this.value = value;
    }

    public Item(Item item){
        this(item.name, item.value);
    }

    public int getValue(){return value;}
    public String getName(){return name;}

    public String toString(){
        return name + " ($" + value +")";
    }
}
