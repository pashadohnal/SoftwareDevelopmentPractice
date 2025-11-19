# Array, ArrayList, Vector, List
| Type        | Recommended Use Case                                                  |
|-------------|------------------------------------------------------------------------|
| **Array**   | Performance-critical, primitive-type storage                          |
| **ArrayList** | Most dynamic list needs in single-threaded code                     |
| **Vector**  | When built-in synchronization is required                             |
| **List**    | As a reference type for flexibility and abstraction                   |

# Naming Conventions
| Element        | Convention           | Style Example              | Notes                                                   |
|----------------|----------------------|----------------------------|----------------------------------------------------------|
| **Class**      | UpperCamelCase       | `CustomerAccount`          | Should be a noun or noun phrase                         |
| **Interface**  | UpperCamelCase       | `Runnable`, `Serializable` | Describes capability or behavior                        |
| **Method**     | lowerCamelCase       | `calculateTotal()`         | Should be a verb or verb phrase                         |
| **Variable**   | lowerCamelCase       | `totalAmount`, `userList`  | Descriptive, avoid abbreviations                        |
| **Constant**   | UPPER_SNAKE_CASE     | `MAX_SIZE`, `DEFAULT_PORT` | Must be `static final`                                  |
| **Package**    | lowercase            | `com.example.myapp`        | Use reversed domain name for uniqueness                 |
| **Enum Type**  | UpperCamelCase       | `Status`, `Color`          | Enum constants use UPPER_SNAKE_CASE                     |
| **Annotation** | UpperCamelCase       | `@Override`, `@Deprecated` | Used for metadata and compiler hints                    |