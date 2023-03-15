# Project Title: CareerQ

### Team 8: James Kim, Lucas Stevenson, Rio Taiga

#### Problem/Issue to Resolve

Attending a career fair is a crucial step in the job search process for students and a valuable opportunity for companies to recruit top talent. However, our experiences of attending the career fair at SJSU were overwhelming and disorganized. The sheer number of people flowing in and out of the ballroom where the fair is held can lead to long, confusing lines and an overall poor experience for students, companies, and recruiters.

#### High Level Description of our solution

Our proposed solution, CareerQ, addresses these challenges by providing a user-friendly web application that streamlines the career fair experience for students, recruiters, and schools. Through CareerQ, students can easily browse participating companies, join queues for the companies they are interested in, and receive notifications when it is their turn to speak with recruiters. Recruiters can use CareerQ to manage their queues and collect feedback from students. Schools can use CareerQ to create and manage career fair events, accept company requests to participate, and track attendance data.

#### Assumptions / Operating Environment

Java Virtual Machine (JVM)

#### Intended usage

Intended usage for recruiters/companies, students and schools.
- Students: use it to see queue lengths and sign up to talk to companies
- Recruiters/companies: use it to sign up for the career fair.
- Schools: use it to keep track of the amount of people attending the career fair

#### Functionality

* We can provide a solution with organization where they provide the user interface for scheduling which companies that attendees are going to and manage communication with the recruiters. 

* Data Collection and Analysis in order to keep up with how many people are lined up to associate with each Company’s recruiters (queue system)

* Provide a solution to traffic flow due to the large number of attendees that are joining. 

#### Operations for Users:

Students:
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
Recruiter
- Register which Company you are from 
- List the Job opening and able to post the availability 

School/Admin
- Create the career fair event on the website
- Accept company requests to join career fair
- Add/delete companies
- Set a time frame for the career fair (what times people are able to sign up).

