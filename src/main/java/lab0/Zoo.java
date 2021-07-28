package lab0;

import java.util.ArrayList;
import java.util.List;

class Animal {
  private String name;

  public Animal(String pName) {
    name = pName;
  }

  public String getName() {
    return name;
  }
}

class Monkey extends Animal {
  public Monkey(String pName) {
    super(pName);
  }

  @Override
  public String toString() {
    return "Monkey:" + getName();
  }
}

class Chimp extends Animal {
  public Chimp(String pName) {
    super(pName);
  }

  @Override
  public String toString() {
    return "Chimp:" + getName();
  }
}

class Zoo<E> {
  private List<E> animals;

  public Zoo() {
    // Substitutability in action (1) -
    // Supertype reference to subtype object
    animals = new ArrayList<E>();
  }

  public void addAnimal(E newAnimal) {
    animals.add(newAnimal);
  }

  @Override
  public String toString() {
    // ArrayList.toString() is invoked at runtime
    return animals.toString();
  }
}

class Bird {

  public String name;
  public String specie;

  public Bird(String name, String specie) {
    this.name = name;
    this.specie = specie;
  }

  @Override
  public String toString() {
    return specie + ":" + name;
  }
}
