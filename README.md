# EventRegistration-Project
Created A Simple java-hibernate Stand-alone application to register an employee for an event with 
following options:-

1.  Show all event.
2.  Show all Employees with its event mapped.
3.  Subscribe for Event.
4.  UnSubscribe from Event.
5.  Add New Employee and subscribe for event(Register employee for event).
6.  Show the event by id.
 
 Implemented:-
- Second level ehcache -cache.
- @ManyToMany with separate join table feature.
- Used updatable = false .
- Used Query level cache too.
- Generate the event id using IDENTITY feature.
- Used EAGER fetching to fetch employee along with event detail 


