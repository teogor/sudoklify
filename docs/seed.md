# Managing Randomness for Sudoku Generation

This documentation explores the `Seed` class in the `sudoklify` library, a powerful tool for
controlling and customizing the randomness of your Sudoku puzzles. `Seed` offers a safe and
structured way to handle different types of seeds, ensuring reproducible and consistent puzzle
generation while providing flexibility and control.

## **Key Benefits**

* **Type Safety and Validation:** Guarantees valid seed values, preventing errors and unexpected
  behavior.
* **Random vs. Explicit Seeds:** Choose between randomly generated seeds for variety or specific
  values for controlled puzzle creation.
* **Intuitive Functions:** Easily create, manipulate, and access seeds with clear and convenient
  functions.
* **Reproducible Puzzles:** Generate consistent Sudoku grids using the same seed for sharing or
  testing purposes.

## **Understanding Seed Types**

* **`Random`:** Generates a new seed based on the system's random number generator, ensuring
  different puzzles each time.
* **`Explicit`:** Allows you to specify a specific long value as the seed, enabling precise control
  over the generated puzzle.

## **Key features**

* **`toRandom()` function:** Converts a `Seed` object to a `kotlin.random.Random` instance.
* **`Explicit` constructor:** Allows creation of explicit seeds with custom values.
* **`nextSeed()` function:** Generates a new `Seed` object with an incremented value (if
  applicable).
* **`copy()` function:** Creates a copy of the `Seed` object with an optional new value.
* **`toString()` function:** Provides a human-readable representation of the `Seed` object.

## **Working with Seeds**

### **Creating Seeds**

#### 1. **Creating a `Random` seed**

```kotlin
val randomSeed: Seed = Seed.Random()
println(randomSeed) // Output: RandomSeed(-7367563790280219005)
```

#### 2. **Creating an `Explicit` seed**

There are two ways to create an `Explicit` object:

#### 2.1 **Using `toSeed()`**

The `toSeed()` extension function directly converts the `Long` value to an `ExplicitSeed`:

```kotlin
val longSeed: Long = 123
val seedFromLong = longSeed.toSeed()

println(seedFromLong) // Output: ExplicitSeed(123)
```

#### 2.2 **Using `createSeed()`**

The `createSeed()` function offers a more explicit way to create a `Seed`:

```kotlin
val longSeed: Long = 123
val seedFromCreate = createSeed(longSeed)

println(seedFromCreate) // Output: ExplicitSeed(123)
```

**Choosing the right method:**

Both methods achieve the same outcome, so the choice depends on your preference and coding style.

* **`toSeed()`:** More concise and convenient for direct conversion.
* **`createSeed()`:** More explicit and readable, especially if you need additional parameters or
  logic in the future.

**Additional notes:**

* Remember that any negative `Long` values will trigger an exception with both methods.

### **Utilizing Seeds**

#### 1. **Getting the next seed**

```kotlin
val nextSeed = explicitSeed.nextSeed()
println(nextSeed) // Output: ExplicitSeed(43)
```

#### 2. **Copying a seed**

```kotlin
val copiedSeed = explicitSeed.copy(seed = 50)
println(copiedSeed) // Output: ExplicitSeed(50)
```

#### 3. **Creating a `kotlin.random.Random` instance**

```kotlin
val randomInstance = explicitSeed.toRandom()
```

### **Additional notes**

* You can use the `toRandom()` function to access the actual random number generator instance.
* Refer to the source code for further details on specific functionalities and potential exceptions.

### References

* [Source code for `Seed` class](https://github.com/teogor/sudoklify/blob/main/sudoklify-common/src/main/kotlin/dev/teogor/sudoklify/common/types/Seed.kt){:target="_blank"}
* [API documentation for `Seed` class](../html/sudoklify-common/dev.teogor.sudoklify.common.types/-seed/index.html){:target="_blank"}
* [Source code for `SeedExtensions`](https://github.com/teogor/sudoklify/blob/main/sudoklify-ktx/src/main/kotlin/dev/teogor/sudoklify/ktx/SeedExtensions.kt){:target="_blank"}
* [API documentation for `SeedExtensions`](../html/sudoklify-ktx/dev.teogor.sudoklify.ktx/index.html){:target="_blank"}
* [Full `sudoklify` library documentation](../html){:target="_blank"}
