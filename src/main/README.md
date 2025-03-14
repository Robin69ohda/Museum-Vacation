# **_The Design Patterns Use_**

# ****Design Pattern: Singleton****

Why Singleton for the Database Class?

**Centralized Access**

    Ensures a single, consistent Database instance is shared across the application.

**Resource Efficiency**

    Prevents multiple instances, reducing memory usage and avoiding synchronization issues.

**Simplified Coordination**

    Eliminates the need to pass references, ensuring easier maintenance and fewer bugs.

**Lazy Initialization**

    Instance is created only when first accessed, optimizing resource usage.

**Benefits**

    Ensures a unified source of truth.

    Simplifies testing with predictable behavior.

Using Singleton ensures consistency, efficiency, and simplicity in managing the application's data.

# ****Design Pattern: Command****

Why Command for Group Modifications?

**Encapsulation of Requests**

    The Command pattern encapsulates group modification actions (AddMember, RemoveMember, AddGuide)
    as objects, making them reusable and decoupled from the invoker.

**Flexibility and Extendability**

    Adding new commands (e.g., UpdateGuide) is straightforward without modifying existing code, adhering to the Open-Closed Principle.

**Centralized Execution Logic**

    Each command implements a common execute method, simplifying the handling of various operations while enabling centralized control.

**Examples**

`AddMember Command`

    Action: Adds a member to a group and logs the result.
    Error Handling: Logs specific exceptions like GroupThresholdException.

`RemoveMember Command`

    Action: _Removes a member from a group and logs the result.
    Error Handling: Handles cases where the person does not exist in the group.

`AddGuide Command`

    Action: Assigns a professor as a guide, ensuring the person meets the required type and no guide already exists.
    Error Handling: Throws exceptions for invalid guide types or duplicate guides.

**Benefits**

    Simplifies managing group modifications by isolating logic in distinct classes.
    Supports undo/redo functionality if extended in the future.
    Encourages modularity, making the code easier to test and maintain.
    The Command pattern ensures that actions are clearly defined, extensible, and easy to manage, enhancing the flexibility and robustness of the application.

# Design Pattern: Observer

Why Observer for Museum and Professor?

**Notification Mechanism**

    The Observer pattern allows Museum (the Subject) to notify Professor instances (the Observers) about specific events.

**Decoupling**

    The Museum class does not need to know the details of how Professor handles notifications, ensuring low coupling between the Subject and Observers.

**Examples**

`Museum.notifyGuide`

    Action: Iterates through all guides and calls the update method to notify them of an event.
    Customization: Each Professor can define how it processes the notification (e.g., logging event details).

**Benefits**

    Simplifies broadcasting events to multiple observers.
    Makes the system extensible by adding new observer types without modifying the subject.
    The Observer pattern enhances flexibility and modularity by decoupling event producers from consumers.

# Design Pattern: Builder

Why Builder for Museum?

**Simplifying Object Creation**

    The Builder pattern simplifies the creation of Museum objects with many optional fields (e.g., manager, foundingYear, phoneNumber).

**Readable and Flexible Code**

    The step-by-step construction process makes the code more readable and intuitive, especially when dealing with constructors requiring numerous parameters.

**Examples**

`Museum.Builder`

    Mandatory Fields: Ensures required properties like name, code, supervisorCode, and location are always set.
    Optional Fields: Allows selective inclusion of optional attributes like fax or email using method chaining.

**Benefits**

    Avoids the need for large, complex constructors.
    Makes object creation more flexible and maintainable.
    The Builder pattern provides a clean and scalable approach to constructing complex objects like Museum.