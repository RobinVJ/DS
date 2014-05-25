package com.robin.ds.hashing;

public class HashTableBuilder {

   public static <E> DirectAddressTable<E> createDirectAddressTable(int keyMax) {
      return new DirectAddressTable<E>(keyMax);
   }

   public static <K, E> ChainHashTable<K, E> getDivisionBasedHashTable(int size) {
      IHashFunction<K> divisionHashFunction = new DivisionHash<K>(size);
      return new ChainHashTable<K, E>(size, divisionHashFunction);
   }

   public static <K, E> ChainHashTable<K, E> getMultiplicationBasedHashTable(int size) {
      IHashFunction<K> multiplicationHashFunction = new MultiplicationHash<K>(size);
      return new ChainHashTable<K, E>(size, multiplicationHashFunction);
   }

   public static <K, E> OpenAddressingTable<K, E> getLinearProbeOpenAddressingTable(int size) {
      IHashFunction<K> multiplicationHashFunction = new MultiplicationHash<K>(size);
      IProbeFunction probeFunction = new LinearProbing(size);
      return new OpenAddressingTable<K, E>(size, multiplicationHashFunction, probeFunction);
   }

   public static <K, E> OpenAddressingTable<K, E> getQuadraticProbeOpenAddressingTable(int size) {
      IHashFunction<K> multiplicationHashFunction = new MultiplicationHash<K>(size);
      IProbeFunction probeFunction = new QuadraticProbing(size);
      return new OpenAddressingTable<K, E>(size, multiplicationHashFunction, probeFunction);
   }

   public static <K, E> DoubleHashingOpenAddressingTable<K, E> getDoubleHasingOpenAddressingTable(int size) {
      return new DoubleHashingOpenAddressingTable<K, E>(size);
   }

}
