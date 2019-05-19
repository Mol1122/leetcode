/* Please design a simplified parking lot that have multiple levels and support different vehicle sizes.

Notice that, this should be a very civilized parking lot, which means:

For the Parking Lot (not each level), smaller vehicles should always park at the smaller size spots until there is no such spot, then they will seek the slightly larger spots.

For example, compact cars should first try to park at compact spots, then large spots.
 */

/* ParkingSpot.java

A parking spot should have a size of either compact or large for compact and large vehicles.

It should have methods associated with its status, i.e. empty, occupied etc.

Notice that compact cars can park in large spots but large cars cannot park in compact spots.  */
/**
 * enum VehicleSize {
 *   Compact(1)
 *   Large(2)
 * }
 *
 * public abstract class Vehicle {
 *   public abstract VehicleSize getSize();
 * }
 */

public class ParkingSpot {
  private Vehicle vehicle;
  private final VehicleSize size;
  
  
  public ParkingSpot(VehicleSize size) {
      this.size = size;
  }

  public void park(Vehicle v) {
      vehicle = v;
  }

  public void leave() {
      vehicle = null;
  }

  public Vehicle getVehicle() {
      return vehicle;
  }
  
  boolean fit(Vehicle v) {
      if (vehicle == null && v.getSize().equals(VehicleSize.Compact)) {
          return true;
      } else {
          return vehicle == null && this.size.equals(VehicleSize.Large);
      }
  }
}

/* 
Level.java
One level of a parking lot will have multiple spots. 
Let's assume half of them are compact and the others being large ones. 
If there are odd number of spots, make the large spot more. */
public class Level {
  private final List<ParkingSpot> spots;
  
  public Level(int numOfSpots) {
      spots = new ArrayList<>(numOfSpots);
      for (int i = 0; i < numOfSpots; i++) {
          spots.add(new ParkingSpot(VehicleSize.Large));
      }
  }

  public boolean hasSpot(Vehicle v) {
      for (ParkingSpot s : spots) {
          if (s.fit(v)) {
              return true;
          }
      }
      return false;
  }

  public boolean park(Vehicle v) {
      for (ParkingSpot s : spots) {
          if (s.fit(v)) {
              s.park(v);
              return true;
          }
      }
      return false;
  }

  public boolean leave(Vehicle v) {
      for (ParkingSpot s : spots) {
          if (s.getVehicle() != null && s.getVehicle().equals(v)) {
              s.leave();
              return true;
          }
      }
      return false;
  }
}

/* ParkingLot.java
A parking lot should have multiple levels. It should be able to park and leave a vehicle. */
public class ParkingLot {
  private final Level[] levels;
  
  public ParkingLot(int numLevels, int numSpotsPerLevel) {
      levels = new Level[numLevels];
      for (int i = 0; i < numLevels; i++) {
          levels[i] = new Level(numSpotsPerLevel);
      }
  }

  public boolean hasSpot(Vehicle v) {
      for (Level level : levels) {
          if (level.hasSpot(v)) {
              return true;
          }
      }
      return false;
  }

  public boolean park(Vehicle v) {
      for (Level level : levels) {
          if (level.park(v)) {
              return true;
          }
      }
      return false;
  }

  public boolean leave(Vehicle v) {
      for (Level level : levels) {
          if (level.leave(v)) {
              return true;
          }
      }
      return false;
  }
}