package test.robin.ds.hashing;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.robin.ds.hashing.DirectAddressTable;

public class TestDirectAddressTable {

   @Test
   public void create() {
      DirectAddressTable<String> dat = new DirectAddressTable<>(4);
      assertTrue(dat.isSlotEmpty(0));
      assertEquals(4, dat.emptySlotCount());
      assertEquals(4, dat.size());
   }

   @Test(expected = IllegalArgumentException.class)
   public void createInvalid() {
      new DirectAddressTable<>(0);
   }

   @Test
   public void lookUp() {
      DirectAddressTable<String> dat = new DirectAddressTable<>(4);
      dat.insert("1", 1);
      dat.insert("2", 2);
      dat.insert("1", 3);
      dat.insert(null, 0);
      assertEquals("1", dat.lookUp(3));
      assertEquals("2", dat.lookUp(2));
      assertEquals("1", dat.lookUp(1));
      assertEquals(null, dat.lookUp(0));
   }
   
   @Test
   public void slotCount() {
      DirectAddressTable<String> dat = new DirectAddressTable<>(4);
      assertTrue(dat.isSlotEmpty(3));
      String oldVal = dat.insert("Value3", 3);
      assertNull(oldVal);
      assertFalse(dat.isSlotEmpty(3));
      assertEquals(3, dat.emptySlotCount());
      
      oldVal =dat.insert(null, 3);
      assertEquals("Value3", oldVal);
      assertEquals(3, dat.emptySlotCount());
      
      oldVal = dat.insert("Value33", 3);
      assertEquals(null, oldVal);
      assertEquals(3, dat.emptySlotCount());
     
      dat.delete(3);
      assertEquals(4, dat.emptySlotCount());
      dat.delete(3);
      assertEquals(4, dat.emptySlotCount());
   }

   @Test(expected = IllegalArgumentException.class)
   public void deleteInvalid() {
      DirectAddressTable<String> dat = new DirectAddressTable<>(2);
      dat.delete(3);
   }

   @Test(expected = IllegalArgumentException.class)
   public void lookUpInvalid() {
      DirectAddressTable<Integer> dat = new DirectAddressTable<>(2);
      dat.lookUp(-3);
   }

   @Test(expected = IllegalArgumentException.class)
   public void insertInvalid() {
      DirectAddressTable<BigDecimal> dat = new DirectAddressTable<>(2);
      dat.insert(BigDecimal.ZERO, 3);
   }

}
