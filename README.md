<b>Purpose</b>
<p>
LNext cabs allow customers to automatically book the nearest available cab.  In the release 0.0 we are providing a simple functionality where the user will enter her/ his name along with location details. The nearby driver will be auto assigned.

<b>Web-Services Details</b><p>
As of now four web-services are added. 
Here is the swagger URL: http://localhost:8082/swagger-ui.html#/

1). <b>getDriverName</b><p>
Description: This web-service will return the nearest driver name.
URL: http://localhost:8082/getDriverName
Input: {"customerLatitude":19.103938,"customerLongitude":72.888382,"customerName":"Divyang Bhimani"}
Output: {"applicationCode":"200","applicationResponse":"Success","driverName":"Dinesh","customerName":"Divyang Bhimani"}

2). <b>bookcab</b><p>
Description: This web-service takes Customer Name, Customer Latitude, Customer Longitude. It first fetchs the nearest available driver from 'driver_details' table. Insert the details in 'booking_details' table and then update the driver status to BUSY in 'driver_details'. And return the name of assigned driver.
URL: http://localhost:8082/bookcab
Input: {"customerLatitude":19.103938,"customerLongitude":72.888382,"customerName":"Divyang Bhimani"}
Output: {"applicationCode":"200","applicationResponse":"Success","driverName":"Dinesh","customerName":"Divyang Bhimani"}

3). <b>getAllBookings</b><p>
Description: This web-service will return all the bookings till date.
URL: http://localhost:8082/getAllBookings
Input: NA
Output: {"applicationCode":"200","applicationResponse":"Success","bookingDetails":[{"orderNumber":1,"customerName":"Divyang Bhimani","customerLatitude":19.103938,"customerLongitude":72.888382,"driver":{"did":4,"driverName":"Dinesh","latitude":19.11058,"longitude":72.886738,"status":"BUSY"},"bookingDate":"2020-06-24T11:26:25.000+00:00"}]}

4). <b>getAllDrivers</b><p>
Description: This web-service will return a list of all the drivers along with the customer name (if the cab is booked)
URL: http://localhost:8082/getAllDrivers
Input: NA
Output: {"applicationCode":"200","applicationResponse":"Success","drivers":[{"driverName":"Mahesh","status":"AVAILABLE","customerName":null},{"driverName":"Nandu","status":"AVAILABLE","customerName":null},{"driverName":"Ramesh","status":"AVAILABLE","customerName":null},{"driverName":"Suresh","status":"AVAILABLE","customerName":null},{"driverName":"Dinesh","status":"BUSY","customerName":"Divyang Bhimani"}]}
