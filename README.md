# MovieRama
A MovieRama assignment


Steps to run the back end
1) Create a Connection in MySQLWorkbench which runs at port 3306
2) Create a movierama schema (create new schema movierama);
3) Run DemoApplication from an IDE of your choice (JDK is 11)


Some instructions for Testing the App
Download Postman or insomnia
And send some requests from there
Some request such as like, dislike, add movie, need authentication
you have to register, then you have to verify your "mail"(there is a link in id log). if you dont after 10 minutes, your account gets cancelled and you have to make new one.
When you have successfully created the account, you have to go to /authenticate. put your credentials. if they are correct you will get a jwt token.
next step is to copy the token, create a new header with the name Authorization with value: Bearer $yourjwttoken(thats for actions, such us voting and add movies)

the post requests:
the register request body is like this:
{
"username":" ",
"firstName":" ",
"lastName":" ",
"email":" ",
"password":""
}

the authenticate request body is like this:
{
"username":" ",
"password":""
}


the addMovie request body is like this:
{
"title":" ",
"description":""
}
also needs Header:
Authorization $yourJwtToken

the voting request body is like this:
{
"movie":" ",
"liked":"truth/false",
disliked:"truth/false"
}
Authorization $yourJwtToken

liked and disliked cannot have the same value. it would be two standar bodies that would be sent from frontEnd.


Steps to run the frontend 
1) open movieramaui in VSC
2) open the IDE terminal
3) run the command npm start
4) Not all Components are Workable




