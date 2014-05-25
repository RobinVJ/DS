package test.robin.ds.hashing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.robin.ds.hashing.ChainHashTable;

public class TestChainHashTable {

   @Test
   public void create() {
      ChainHashTable<Integer, String> dat = new ChainHashTable<>(4);
      assertTrue(0 == dat.count());
      assertTrue(dat.isEmpty());
   }

   @Test(expected = IllegalArgumentException.class)
   public void createInvalid() {
      new ChainHashTable<>(0);
   }

   @Test
   public void lookUp() {
      ChainHashTable<String, Integer> dat = new ChainHashTable<>(5);
      dat.insert("1", 1);
      dat.insert("2", 2);
      dat.insert("3", 1);
      assertEquals(null, dat.lookUp(null));
      dat.insert(null, 0);
      assertEquals(Integer.valueOf(1), dat.lookUp("3"));
      assertEquals(Integer.valueOf(2), dat.lookUp("2"));
      assertEquals(Integer.valueOf(1), dat.lookUp("1"));
      assertEquals(Integer.valueOf(0), dat.lookUp(null));
   }

   @Test
   public void insertDeleteLookUp() {
      ChainHashTable<Integer, String> dat = new ChainHashTable<>(4);
      assertTrue(dat.isEmpty());
      String oldVal = dat.insert(3, "Value3");
      assertNull(oldVal);
      assertFalse(dat.isEmpty());
      assertEquals(1, dat.count());

      oldVal = dat.insert(3, null);
      assertEquals("Value3", oldVal);
      assertEquals(1, dat.count());

      oldVal = dat.insert(3, "Value33");
      assertEquals(null, oldVal);
      assertEquals(1, dat.count());

      dat.delete(100);
      assertEquals(1, dat.count());
      dat.delete(3);
      assertEquals(0, dat.count());
      dat.delete(3);
      assertEquals(0, dat.count());

      dat = new ChainHashTable<>(1);
      dat.insert(1, "1");
      dat.insert(2, "2");
      dat.insert(3, "3");
      dat.insert(4, "4");
      assertEquals(4, dat.count());

      dat.delete(4);
      assertEquals(3, dat.count());
      assertEquals(null, dat.lookUp(4));
      assertEquals("3", dat.lookUp(3));

      dat.insert(44, "4");
      assertEquals(4, dat.count());

      dat.delete(1);
      assertEquals(3, dat.count());
      assertEquals("3", dat.lookUp(3));
      assertEquals("2", dat.lookUp(2));

      dat.delete(3);
      assertEquals(2, dat.count());
      assertEquals("2", dat.lookUp(2));
      assertEquals("4", dat.lookUp(44));
   }

}
