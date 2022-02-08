### Hotel booking.

Application provides an opportunity to book apartment if you are guest, and eihter manage users requests if you are manager.

# Feature
Available functionality depend on the user's role (guest, manager).Implemented layered architecture with design patterns are: Singelton, Command, Proxy, Factory, Builder.
Used technologies : Maven, Tomcat, JSP, JSTL, MySQL, JDBC, jBCrypt, JUnit, Mockito.


## Guest:
- Placing request with ability of choosing check-in date, check-out date, quantity persons, apartment category, transfer and menu.
- View the list of confirmed requests.
- View photo of apartments.
- Log in.
- Sign Up.
- Change interface language RU<->EN.

## Manager:
Same features as guest with ability to:
- View list of requests.
- Update request.
- Delete request.
- Select list of only new requests.
- View guest data.
- Assemble list of offers for each request.
- Select suitable apartment for each request.
- View offer list.
- Update offer statuses.
- Update offer price.
- View apartment list.
- Add new apartment.
- Update apartment data.
- Delete apartment.
- View timesheet of booked apartments.
- Update guest data and VIP/NonGrata/Manager statuses.
- Pagination on each table.
- Sorting on each table.
- Search on each table.




