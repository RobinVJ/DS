package test.robin.ds.hashing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.robin.ds.hashing.HashTableBuilder;
import com.robin.ds.hashing.OpenAddressingTable;

public class TestOpenAddressingHashTable {

   /**
    * To test for bad hash codes - where probing will be a must
    * 
    * @author robin
    * 
    */
   static class BadHashObject {
      @Override
      public int hashCode() {
         return 1;
      }

      @Override
      public String toString() {
         return super.hashCode() + "";
      }
   }

   @Test
   public void testOpenAddressingTables() {
      OpenAddressingTable<Integer, String> dat1 = new OpenAddressingTable<>(4);
      create(dat1);

      OpenAddressingTable<Integer, String> dat2 = HashTableBuilder.getQuadraticProbeOpenAddressingTable(4);
      create(dat2);
      boolean failed = false;
      try {
         HashTableBuilder.getLinearProbeOpenAddressingTable(0);
      } catch (IllegalArgumentException e) {
         failed = true;
      }
      assertTrue(failed);
      failed = false;
      try {
         HashTableBuilder.getQuadraticProbeOpenAddressingTable(0);
      } catch (IllegalArgumentException e) {
         failed = true;
      }
      assertTrue(failed);

      OpenAddressingTable<BadHashObject, String> table1 = HashTableBuilder.getLinearProbeOpenAddressingTable(4);
      insertDeleteLookUp(table1, 4);
      table1 = HashTableBuilder.getQuadraticProbeOpenAddressingTable(4);
      insertDeleteLookUp(table1, 4);

      OpenAddressingTable<BadHashObject, Integer> table2 = HashTableBuilder.getQuadraticProbeOpenAddressingTable(4);
      lookUp(table2, 4);
      table2 = HashTableBuilder.getLinearProbeOpenAddressingTable(4);
      lookUp(table2, 4);
   }

   @Test(expected = IllegalArgumentException.class)
   public void createInvalid() {
      new OpenAddressingTable<>(0);
   }

   private void create(OpenAddressingTable<Integer, String> table) {
      assertTrue(0 == table.count());
      assertTrue(table.isEmpty());
      Integer anyValue = Integer.valueOf(9);
      assertEquals(null, table.lookUp(anyValue));// empty table
      table.delete(anyValue);
   }

   public void lookUp(OpenAddressingTable<BadHashObject, Integer> table, int initialSize) {
      BadHashObject obj1 = new BadHashObject();
      BadHashObject obj2 = new BadHashObject();
      BadHashObject obj3 = new BadHashObject();
      assertEquals(null, table.lookUp(new BadHashObject()));
      table.insert(obj1, 1);
      table.insert(obj2, 2);
      table.insert(obj3, 1);
      assertEquals(null, table.lookUp(null));
      table.insert(null, 0);
      assertEquals(Integer.valueOf(1), table.lookUp(obj1));
      assertEquals(Integer.valueOf(2), table.lookUp(obj2));
      assertEquals(Integer.valueOf(1), table.lookUp(obj3));
      assertEquals(Integer.valueOf(0), table.lookUp(null));

      table.insert(new BadHashObject(), 0);
      assertEquals(initialSize + 1, table.count());
      table.insert(obj2, 22);
      table.insert(obj3, 11);
      assertEquals(initialSize + 1, table.count());
      assertEquals(Integer.valueOf(22), table.lookUp(obj2));
      assertEquals(Integer.valueOf(11), table.lookUp(obj3));
   }

   public void insertDeleteLookUp(OpenAddressingTable<BadHashObject, String> dat, int initialSize) {
      BadHashObject obj1 = new BadHashObject();
      BadHashObject obj2 = new BadHashObject();
      BadHashObject obj3 = new BadHashObject();
      BadHashObject obj4 = new BadHashObject();
      BadHashObject obj44 = new BadHashObject();

      assertTrue(dat.isEmpty());
      dat.insert(obj2, "Value2");
      String oldVal = dat.insert(obj1, "Value3");
      assertNull(oldVal);
      assertFalse(dat.isEmpty());
      assertEquals(2, dat.count());

      oldVal = dat.insert(obj1, null);
      assertEquals("Value3", oldVal);
      assertEquals(2, dat.count());

      oldVal = dat.insert(obj1, "Value33");
      assertEquals(null, oldVal);
      assertEquals(2, dat.count());

      dat.delete(obj1);
      assertEquals(1, dat.count());
      dat.delete(obj3);
      assertEquals(1, dat.count());
      dat.delete(obj2);
      assertEquals(0, dat.count());

      dat.insert(null, null);
      assertEquals(1, dat.count());
      dat.delete(null);
      assertEquals(0, dat.count());
      // ---------------------Fresh
      dat.insert(obj1, "1");
      dat.insert(obj2, "2");
      dat.insert(obj3, "3");
      dat.insert(obj4, "4");
      assertEquals(4, dat.count());

      dat.delete(obj4);
      assertEquals(3, dat.count());
      assertEquals(null, dat.lookUp(obj4));
      assertEquals("3", dat.lookUp(obj3));

      dat.insert(obj44, "4");
      assertEquals(4, dat.count());

      dat.delete(obj1);
      assertEquals(3, dat.count());
      assertEquals("3", dat.lookUp(obj3));
      assertEquals("2", dat.lookUp(obj2));

      dat.delete(obj3);
      assertEquals(2, dat.count());
      assertEquals("2", dat.lookUp(obj2));
      assertEquals("4", dat.lookUp(obj44));

      dat = new OpenAddressingTable<>(2);
      dat.insert(new BadHashObject(), "1");
      dat.insert(new BadHashObject(), "1");
      dat.insert(new BadHashObject(), "1");
      dat.insert(new BadHashObject(), "1");
      dat.insert(new BadHashObject(), "1");
      dat.insert(new BadHashObject(), "1");
      dat.insert(new BadHashObject(), "1");
      dat.insert(new BadHashObject(), "1");
      dat.insert(new BadHashObject(), "1");
   }

}
