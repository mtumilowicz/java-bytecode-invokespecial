[![Build Status](https://travis-ci.com/mtumilowicz/java-bytecode-invokespecial.svg?branch=master)](https://travis-ci.com/mtumilowicz/java-bytecode-invokespecial)

# java-bytecode-invokespecial
The main goal of this project is to show differences between 
`invokespecial` vs `invokevirtual`.


_Reference_: https://www.artima.com/underthehood/invocationP.html

# preface
* **invokestatic** - When the Java virtual machine invokes a class 
method, it selects the method to invoke based on the type of the 
object reference, which is always known at compile-time.

* **invokevirtual** - On the other hand, when the virtual machine 
invokes an instance method, it selects the method to invoke based 
on the actual class of the object, which may only be known at run time.

# dynamic linking
Because Java programs are dynamically linked, references to methods 
initially are symbolic. To resolve a symbolic reference, the JVM 
locates the method being referred to symbolically and replaces the 
symbolic reference with a direct reference. A direct reference, 
such as a pointer or offset, allows the virtual machine to invoke 
the method more quickly if the reference is ever used again in the 
future.

# ivokespecial
`invokespecial` is used in three situations in which an instance method 
must be invoked based on the type of the reference, not on the class 
of the object. The three situations are:

* invocation of instance initialization (`<init>`) methods
* invocation of private methods
* invocation of methods using the super keyword

`invokespecial` differs from `invokevirtual` primarily in that 
`invokespecial` selects a method based on the type of the reference 
rather than the class of the object. In other words, it does static 
binding instead of dynamic binding. In each of the three situations 
where `invokespecial` is used, dynamic binding wouldn't yield the 
desired result.

# project description   
* Parent bytecode (for `locate()`):
    ```
    locate()Ljava/lang/String;
     L0
      LINENUMBER 10 L0
      ALOAD 0
      INVOKESPECIAL Parent.where ()Ljava/lang/String;
      ARETURN
     L1
      LOCALVARIABLE this LParent; L0 L1 0
      MAXSTACK = 1
      MAXLOCALS = 1  
    ```
    if we use `INVOKEVIRTUAL` instead of `INVOKESPECIAL` then
    `(new Child().locate()` returns `"In Child"`