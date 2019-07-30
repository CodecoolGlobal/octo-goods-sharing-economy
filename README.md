# __Assignment: Spring Boot Poject__

Create a REST API using Boot. What is this API supposed to do? It is up to you. You only have to conform to the requirements listed below.

## __Requirements__

1. Use Spring Boot to create the project.
2. Define from 2 to 3 resources.
3. Handle CRUD operations on these resources.
4. Between two of available resources, relationship has to be defined.
5. You HAVE to use H2 or PostgreSQL database.
6. Keep separate concerns of your app - controller, service, repository. Make sure responsibilities are not mixed, e.g. controller is responsible for operating on HTTP layer and passing data further, only.
7. After fullfilling these requirements check the next page :sunglasses:

## __What about front-end?__

If you are willing to learn more front-end you are more than welcome to implement a client application to interact with the API.

## __New requirements__

So you think you've finished your project, huh? Sorry, but successful IT projects are usually a never ending story. So imagine that a CTO comes to you and says:

_You're API is great, it would be nice if we could track it's usage. So please log every operation to a text file using log4j. All errors should be logged as well. Your architecture is S.O.L.I.D. so it won't be a problem for you, will it?_

You agree to do that of course. Than a CMO comes and says:

_Hey, I've seen the API that you've made, it's fantastic. I'd like to ask you for a little modification. When a user deletes something I don't want it to be actually deleted but marked as archived, so that the user doesn't see it but I can access historical data and make my marketing analysis. CTO told me that you know Open Closed Principle well and implementation of this feature won't take long._

You don't want to mess with your CMO because CTO has a crush on her, so you agree again. Now, you're about to leave work and go home but CEO comes in and says:

_Your API is one of our best products. Thus we need to keep it stable. Please keep me informed about any errors that occur. Ideally I'd like your application to send me emails with error details. Is it possible to do that?_

Of course it is. Everything is possible in programming. The next day comes CDO and says:

_Hi, I've got a bunch of csv files that I'd like to import to your system, but making a separate request for each row would take me ages. Can you do something about that?_

Now you should know that only constant in IT world is change.

So the new requirements are:

1. Logging of every CRUD operation and exception to a text file using [log4j](https://logging.apache.org/log4j/2.x/)
2. Data should be safe deleted - archived instead of physically deleted. Users should not see archived data but the data should be present in the database
3. (Optionally) - send an email whenever an status code 500 is to be sent
4. (Optionally) - bulk insert feature from csv using CLI
