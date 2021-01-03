/* ValueObjectListCollector.java

*/

package com.modelgenerated.foundation.dataaccess;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;


/**
 * Collects modelgenerated ValueObjectList for use with java8 streams.
 * Needs user context and method to create the value object list.
 * For example: new ValueObjectListCollector<User,UserList>(userContext, userService::newUserList)
 */

public class ValueObjectListCollector<O extends ValueObject,L extends ValueObjectList<O>> implements Collector<O, L, L> {
   UserContext userContext;
   Function<UserContext, L> listSupplier;

   
   public ValueObjectListCollector(UserContext userContext, Function<UserContext,L> listSupplier) {
       this.userContext = userContext;
       this.listSupplier = listSupplier;
       
   }
   
   private L getListSupplier() {
       return listSupplier.apply(userContext);
   }
   
   @Override
   public Supplier<L> supplier() {
       return this::getListSupplier;
   }

   @Override
   public BiConsumer<L, O> accumulator() {
       return (l, o) -> ((ValueObjectList<O>)l).add(o);
   }

   @Override
   public BinaryOperator<L> combiner() {
       return (left, right) -> {((ValueObjectList<O>)left).addAll((ValueObjectList<O>)right); return left;} ;
   }

   @Override
   public Function<L, L> finisher() {
       return (a) -> a;
   }

   @Override
   public Set<Characteristics> characteristics() {
       Set<Characteristics> set = new HashSet<Characteristics>();
       set.add(Characteristics.IDENTITY_FINISH);
       return set;
   }
}
