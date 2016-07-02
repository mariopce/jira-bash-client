# Jira bash client

## Introduction

I do a lot of things from console. I'm using git flow feature start to start a new task/branch.
I'm using git flow feature to finish task, and I'm using git to commit my code nipped.
I would like to connect:
- git flow
- git comments
- jira
To start finish and comment what happen with task.

Someone can say - but "smart commit" are avaliable on jira - but my company can not enable this feature.
Someone can say that jire CLI is avaliable - but this is not enable in my company too from security reason.

Library https://github.com/rcarz/jira-client emulate browser and you are able to run this library on jira, even CLI is
disabled.

## Usage

### Autentication

There is two ways of auth. You can pass username, password and url by options like bellow:
```
jira -u mario -p password -url tomtom.com
```
or
```
jira --username --password password --url https://jira.tomtom.com
```
or by config file. ( by default conf.prop)
```
jira -c conf.prop
```
Example of conf.prop
```
username=mariopce@gmail.com
password=mario18
url=https://testmario.atlassian.net
project=LNS
```
When you don't pass any arguments for authentication then auth data are taken from conf.prop. In this way console
commands are even shorter (TODO)

### Update task
Add comment
```
jira update --task LNS-11 --addcomment "Bla bla bla"
```
Change status
```
jira update --task LNS-11 --status "In Progress"
```
Change status and add comment. Using different project configuration
jira -c conf2.prop update --task LNS-11 --status "In Progress" --addcomment "Bla  \\n bla bla"

### Checking task user state
```
jira -u mario -p password -url tomtom.com getinfo --user saramakm --filter "status==inprogress"
jira getinfo --user saramakm --filter "status==done"
```

### Create new task (TODO)
```
jira createTask -t "Title Of task" -d "Description of task" -p "LNS-122"
```

add subtask
```
jira  update --task LNS-24 --addsubtask summary=subtask1,description="fdsfs  \\n fdsfds \\n\\r vfsdsd \\fsfs In Progress"
```
### Get information about tasks

Information about task: summary, description, comments
```
jira getinfo --task LNS-123
```
Get all comments from task
```
jira getinfo --task LNS-123 --filter "comments"
```

Filter task and display info
jira getinfo --user admin --filter " AND status=\"In Progress\""

