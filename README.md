# TopRepo
A mini web application that shows top 10 GitHub repositories

GitHub Top Repositories


1. Objectives

This project, TopRepo, is indeed an assignment that asks to provide a simple web application to allow user to show top repositories from GitHub.
2. Requirements
R1: Show top 10 most forked repositories.
R2: Show top 10 pull request (updated) repositories. This is an open issue – requirements is not clear.
R3: Show top repositories in given time period.
R4: Show the trend of each top repository.
R5: This project will be submitted to github and the url will be shared with ebay recruiting team.

Note about R2:
It’s not very clear in the second part of “Build an application to show the top 10 github repositories with most fork and pull request ”. Does it mean the 10 repositories that received the latest updates?

For testing purpose, TopRepo may or should provide a few handy REST api’s to return a skinnier version of top github repositories in JSON.

3. Non Requirements
To improve user experience, the performance of TopRepo could be improved with a background thread that periodically fetches the top repositories and then either caches or persists them locally.

Due to the time constraints, this project will be designed as pass-through proxy with minimal processing. Caching and persistence are purposely neglected.

This project is not meant to be Internet scaled. Therefore, clustering or distributed deployment is not considered.

4. Design
TopRepo is a web application. Its distribution is either in binary form top.war or build the war file from  source code directly.
4.1 Backend
The backend will be implemented in Java.
Technology stack: Spring Boot, apache-common-httpclient, gson, tomcat (or any servlet container).

REST API – TopRepoController:
This api controller takes user request for top 10 (default value) github repositories in terms of either the number of forks or number of updates.

RestClient:
This is a generic HTTP client but tailored to interact with remote github.
GitHub api v3 provides a handy REST api to retrieve a list of repositories. For instance, getting the top 10 forked repositories can be done with
https://api.github.com/search/repositories?q=forks:>1&sort=forks&page=1&per_page=10&order=desc
where query parameter sort take [ stars | forks | updated ]. The default sorting order is DESC, therefore, order=desc can be neglected.
It’s not too clear how the search parameter “s” should be specified. It seemed that its value depends on what the sort is, 
- s=forks:>1 if sort=forks
- s=stars:>1 is sort=stars
What is sort=updated? In this case, q=updated, will lists the repositories that have received the latest update.
Repositories that got latest update (pull requests) can be retrieved by
https://api.github.com/search/repositories?q=updated&sort=updated&page=1&per_page=10

Refer to API doc https://developer.github.com/v3/search/#search-repositories.
4.2 Frontend

Due to the lack of knowledge in UI framework that can work with JSON data objects, UI may be either very primitive such as in raw JSON, or using the JSP.
I may try to learn some jsp framework if time permits!

5. Build and Run

