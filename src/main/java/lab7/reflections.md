**Lab 7 REFLECTION**
--------------------
**Interface**:
- DataService is an interface that defines the getRecipes().
- Allows multiple implementations (such as: SqliteDataService, CsvDataService, mocks).

**Dependency Injection**:
- searchRecipes() accepts a DataService as parameter.
- Allows injecting different data sources (real/unreal) at test and runtime.

**Polymorphism**:
- The method accepts any object that implements DataService, regardless of the implementation.
- Allows flexibility and interchangeability between SQLite or mock objects.

**Mock**:
- During testing, fake DataService implementations can be created using lambdas or anonymous classes.
- Mocks supply hardcoded recipe data, and tests are controlled and predictable.

**Anonymous Classes / Lambdas**:
- Allow inline creation of mock DataService objects.
- Avoids the need to create individual mock classes for each test case.

**Loose Coupling**:
- searchRecipes() logic is loosely coupled with any data source.
- Promotes separation of concerns and simpler code maintenance.

**Testability**:
- Due to DI, interface usage, and loose coupling, the function is easy to test with controlled inputs.
- Test cases can target specific behavior (like: case insensitivity, name/description matching).