# TopRepo

GitHub Top Repositories - A mini web application that shows top 10 GitHub repositories - thanks to eBax!

DISCLAIMER: This is a take-home assignment as part of the interview task for a south bay company. It may not have any value to you at all. However, if you are not familiar with Spring Boot and plan to learn it, or if you need to build a local application to interact with the GitHub, you may find the source code useful! For example, how should you configure your maven script to run your Boot application inside your IDE as a server (instead of just booting up and stopping). 


1. Objectives

This project, TopRepo, is indeed an assignment that asks to provide a simple web application to allow user to show top repositories from GitHub.

2. Requirements

R1: Show top 10 most forked repositories.
R2: Show latest 10 pull-request (updated most recently) repositories. This might be an open issue – requirements is not too clear to me.
R3: Show top repositories in given time period.
R4: Build a simple UI chart to show the trend of each top repository (*).
R5: This project will be submitted to GitHub and the url will be shared with xbay recruiting team.

Note about R2:

It’s not very clear in the second part of “Build an application to show the top 10 github repositories with most fork and pull request ”. Does it mean the 10 repositories that were updated most recently? I think it is but will confirm.

For testing purpose, TopRepo may or should provide a few handy REST api’s to return a skinnier version of top github repositories in JSON.

3. Non Requirements

To improve user experience, the performance of TopRepo could be improved with a background thread that periodically fetches the top repositories and then either caches or persists them locally.

Due to the time constraints, this project will be designed as a pass-through proxy with minimal processing. Caching and persistence are purposely neglected.

This project is not meant to be Internet scaled. Therefore, clustering or distributed deployment is not considered.

4. Design

TopRepo is a web application. Its distribution is either in binary form in TopRepo.jar, or TopRepo.war, or you can build it from  source code directly.

4.1 Backend

The backend will be implemented in Java.

Technology stack: Spring Boot, apache-httpclient, gson, tomcat (or any servlet container).

REST API – TopRepoController:

This api controller takes user request for top 10 (default value) GitHub repositories in terms of either the number of forks or number of updates.

 - @GetMapping("/mostForked") public List<GitRepository> mostForked()
 - @GetMapping("/mostRecentlyUpdated") public List<GitRepository> mostRecentlyUpdated()
 - @GetMapping("/top10") public List<GitRepository> getTopRepositories()
 
 Please refer to their JavaDoc for more details about each api is mapped and used.

RestClient:

This is a generic HTTP client but tailored to interact with GitHub only.

GitHub api v3 provides a handy REST api to retrieve a list of repositories. For instance, getting the top 10 forked repositories can be done with

https://api.github.com/search/repositories?q=forks:>1&sort=forks&page=1&per_page=10&order=desc

where the query parameter "sort" can take [ stars | forks | updated ]. The default sorting order is DESC, therefore, order=desc can be neglected.

It’s not too clear how the search parameter “s” should be specified. It seemed that its value depends on what the sort parameter is, 

- s=forks:>1 if sort=forks
- s=stars:>1 is sort=stars

What is sort=updated? In this case, q=updated, which will list the repositories that have received the latest update up to now.

Repositories that got latest update (pull requests) can be retrieved by

https://api.github.com/search/repositories?q=updated&sort=updated&page=1&per_page=10


Trending repositories:

<center>
<h>Trending</H>
See what the GitHub community is most excited about today.
</center>


https://github.com/trending by default shows a list of repositories that the GitHub community is most excited about. The level of the excitement is represented by the number of stars each repository has received today. As usual, the list is sorted with the one that has the most stars on the top.

Besides daily trending, user can also choose weekly or monthly.

Unfortunately GitHub doesn't provide a public api to allow external api client to query for trending repositories.

There are 2 options if we want to offer trending feature from TopRepo.

Option 1:

  Fetch the trending page directly from GitHub and parse that HTML content and render it in our own style. This can be done either by the backend code or javascript only. This is easiest approach but be aware that parsing HTML is error prone for HTML document may not be well formed.

Option 2:

  Due to the lack of available api's, what we could do - silly but reliable - is to build a service to store the repository-star count information for all possible repositories each day up to a month. Then we can offer the trending feature nearly as good as or even better what GitHub does (both subscription based and on-demand trending on any day within the past 30 days!).

  The daily count information for each top repository needs to be persisted to be durable.

These 3 lists, daily/weekly/monthly after being aggregated, can be kept in memory since they are changed only once a day. They can be cached locally to each worker node.

I can talk about this design further. But I don't think I have the time now.
    

Refer to API doc https://developer.github.com/v3/search/#search-repositories for more details.


4.2 Frontend

Due to the lack of my knowledge in UI framework that can work with JSON data objects, UI may be either very primitive such as in raw JSON, or using the JSP.

I may try to learn some jsp framework if time permits!


5. Build and Run

This assumes that you system already had git, maven, and JDK 1.8 installed.


Pull the source code from GitHub:

git init

git clone https://github.com/lzhang4911/TopRepo

cd TopRepo

Build and run from command line:

./mvn package

java -jar target/top-repo-artifect-0.0.1-SNAPSHOT.jar


You could also run directly from your IDE.

Note that the HTTP port is set to 8081.

Here is the UI:

http://localhost:8081

To see if there is any project done for ebax:

http://localhost:8081/api/top10?q=ebax+updated&sort=updated&per_page=100
