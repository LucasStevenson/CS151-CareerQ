# Project Title: CareerQ

### Team 8: James Kim, Lucas Stevenson, Rio Taiga ###

## Proposal Contributions ##
 * Rio Taiga - On the Proposal page, edited bullet points on Operations for what School/Admin and Company Recruiter should be able to do in the Dashboard. Also provided comment on how the Functionality is supposed to be.
 * Lucas Stevenson - Contributed to user operations, such as the actions students and admins are able to do. Also wrote what the intended usage is for each type of user.
 * James Kim - Proposed problem/issue to resolve, worked on High Level Description of our solution, and composed functionalities for Student and edited part of Company.

## Project Presentation Contributions ##
 * Rio Taiga - Introduction of the project and the purpose of it, discussing the portions of Student Dashboard and Company Dashboard.
 * Lucas Stevenson - Did the project demo and explained how different components are rendered to the screen depending on the type of user that is logged in. Talked about the backend and the libraries used.
 * James Kim - Presented about our Frontend using React framework, provided information on the composite structure, event handler, state management, and data fetch used in the frontend.  

## Code and Report Contributions ##
 * Rio Taiga - Created Student Dashboard, Company Dashboard using ReactJS (focused on frontend) which connects to backend, and created Use Case Diagram as well as State Diagram. 
 * Lucas Stevenson - Implemented the entire backend. Used Java Spark to manage all the API endpoints and JsonWebTokens to handle user authorization. On the frontend, created the Events page, the homepage, event info pages, and the register + login. Also added functionality to the EventManagement page via connecting the UI to the backend.
 * James Kim - Created Class Diagram and Sequence Diagram. Created Admin Dashboard, Event Table, Profile Page, CSS Style Sheet using ReactJS. 

## Problem/Issue to Resolve ##

Attending a career fair is a crucial step in the job search process for students and a valuable opportunity for companies to recruit top talent. However, our experiences of attending the career fair at SJSU were overwhelming and disorganized. The sheer number of people flowing in and out of the ballroom where the fair is held can lead to long, confusing lines and an overall poor experience for students, companies, and recruiters.

## High Level Description of our solution ##

Our proposed solution, CareerQ, addresses these challenges by providing a user-friendly web application that streamlines the career fair experience for students, recruiters, and schools. Through CareerQ, students can easily browse participating companies, join queues for the companies they are interested in, and receive notifications when it is their turn to speak with recruiters. Recruiters can use CareerQ to manage their queues and collect feedback from students. Schools can use CareerQ to create and manage career fair events, accept company requests to participate, and track attendance data.

## Assumptions / Operating Environment ##

Java Virtual Machine (JVM)

## Intended usage ##

Intended usage for recruiters/companies, students and schools.
- Students: use it to see queue lengths and sign up to talk to companies
- Recruiters/companies: use it to sign up for the career fair.
- Schools: use it to keep track of the amount of people attending the career fair

## List of UML Diagrams ##

| Diagram | Description of Diagram |
| ------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
| [Class](https://github.com/LucasStevenson/CS151-CareerQ/blob/main/diagrams/CareerQClassDiagram.pdf)               | In this Class diagram, it shows the relationship between the Student, Admin, Company, and Queue.                                                                          |
| [Use Case](https://github.com/LucasStevenson/CS151-CareerQ/blob/main/diagrams/useCaseDiagram.drawio.pdf)         | In this Use Diagram, the Use Case is displayed. The Users: Admin, Student, and Company have different and/or same use case for this Project.                                                                            |
| [State](https://github.com/LucasStevenson/CS151-CareerQ/blob/main/diagrams/stateDiagram.drawio.pdf)              | In this State Diagram, it shows different states (Student, Admin, Company) after being authenticated. Users are able to perform different tasks depending on what role the Users have.                                                                            |
| [Sequence](https://github.com/LucasStevenson/CS151-CareerQ/blob/main/diagrams/CareerQSequenceDiagram.pdf)        | In this Sequence Diagram, it shows the sequence of user interaction between Student, GUI, userDatabase, Company, and Queue.                        |

## Functionality ##

* We can provide a solution with organization where they provide the user interface for scheduling which companies that attendees are going to and manage communication with the recruiters. 

* Data Collection and Analysis in order to keep up with how many people are lined up to associate with each Company’s recruiters (queue system)

* Provide a solution to traffic flow due to the large number of attendees that are joining. 

## Operations for Users: ##

Student:
- Register/Login: Students can create an account or log in using their university credentials to access the system.
- Browse Companies: Students can view the list of companies attending the career fair.
- Overview Queue Status: Students can see the number of people waiting in line for each company and the estimated wait time.
- Join a Queue: Students can join the queue for a specific company.
- Check Queue Position: Students can view their position in the queue at any time.
- Receive Notifications: Students will be notified either through push notifications or email when their position in the queue is less than 3.
- Leave Queue: Students can leave the queue when they are no longer desired to be in the queue. Pushes all other students’ positions behind this person by 1.
- Participate in a Survey: Students can participate in a survey or feedback form (google form) to provide feedback for a specific company or the general career fair.

Company:
- Register: Companies can register for the career fair and will be added to the list of participating companies. 
- View Queue Status: Companies can monitor the number of students in the queue.
- Accept First Student: Companies can accept the first student in the queue
- Set Queue Threshold: Companies can impose a maximum number of students in the queue. 
- Pause/Resume Queue: Companies can pause or resume their queue for varying situations. 
- Remove Students in the Queue: Companies can manually remove a student from the queue.
- Prioritize Students in Queue: Companies can change the order of students in their queue to first.
- Drop-in/Queue Status: Companies can set either to operate by drop-ins (not using the queue system) or to use the queue system. Optimize for smaller companies or companies with fewer people visiting.    

School/Admin
- Create Event: Admin can create the career fair event on the website.
- Manage Companies: Admin can accept company requests to join career fair. Admin can also Add/delete companies.
- Set Event Timeframe: Set a time frame for the career fair (what times people are able to sign up).

## Solution ##

## Steps to Run the Code ##

This application is split into two parts: the backend and the frontend. First, we need to start up the backend so that the frontend can talk to it.

> To ensure running the backend goes smoothly, please make sure to have the latest version of Java installed

How to start the backend
- run `MainController.java`

How to start the frontend
- run `npm run start` inside the `frontend/` directory

## Snapshot of the Running Program ##
![Register](https://user-images.githubusercontent.com/53506239/236359889-c958512b-6bad-48ae-833c-783918d4026a.jpeg)
![LoginPage](https://user-images.githubusercontent.com/53506239/236359880-b8497989-7ee5-4fd0-bae3-9f8bb4e310cc.jpeg)
![CompanyPage](https://user-images.githubusercontent.com/53506239/236359910-69c95137-c511-44ee-a246-98ec9c15f4c8.jpeg)
![QueueManagement](https://user-images.githubusercontent.com/53506239/236359960-18359e94-d209-442b-8e9e-7e850933422a.jpeg)
![StudentDashboard](https://user-images.githubusercontent.com/53506239/236359931-9b37e5d8-aaa9-4635-b7d8-534af5ff9c3c.jpeg)
![AdminEvent](https://user-images.githubusercontent.com/53506239/236359865-d21023cb-8607-453e-9358-aff19a2e2905.jpeg)
