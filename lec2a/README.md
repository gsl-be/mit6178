# 6.178 Lecture #2 - Command Line, Git, Methods, Switch, Enums, Strings, and Arrays

## More Logistics
* [Instructions on Cloning & Submitting Psets](https://github.mit.edu/6178-2017/pset-instructions)
* You will be given a suite of tests but there are also a suite of hidden tests that staff will use to give you additional feedback. You will not be penalized if you do not pass the hidden suite of tests.
* You will pass the pset if you pass all the tests that were given to you.
* You can review the [Syllabus](https://stellar.mit.edu/S/course/6/ia17/6.178/courseMaterial/topics/topic1/syllabus/Syllabus/Syllabus.pdf) on Stellar for more information.
* We have a schedule of our classes on Stellar [here](https://stellar.mit.edu/S/course/6/ia17/6.178/courseMaterial/topics/topic1/resource/Schedule_(1)/Schedule_(1).pdf)
* If you didn't get a repo for pset #1, please let us know by filling out [this Google form](https://goo.gl/forms/TDHtVW5f3ncpWImE2).
* Please sign up for the [Piazza](https://piazza.com/class/ixbeg17oz79vm) to get updated on pset revisions and other things.

## Command Line
Command-line is just an interface to your computer just like Finder or Windows Explorer. The only difference is that it is text based. The command-line keeps track of what directory/folder you are currently in and you can run commands that can take zero or more arguments.

For __Mac and Linux__ users, we use __Terminal__ as our command-line. For __Windows__ users, we use __Git Bash__.

In the future, when we say "run this command", we mean open up your command-line interface (either Terminal or Git Bash), type that command in the appropriate directory, and hit _Enter_.

<img src="./images/terminal1.jpg" style="margin-right: auto; margin-left: auto; display: block; width: 200px;">

In this picture you can see that the text in the red box tells you where you currently are or what directory you are in. `~` is a shortcut to your home directory. For example for Mac Users, this is usually `/Users/<username>`.

Here are some useful commands:

* `cd` - "change directory"
  * Changes the current directory. In you’re in a directory that has a subdirectory called hello, then cd hello moves into that subdirectory. 
  * `cd ..` moves to the parent directory (the '..' represents the parent directory)
  * `cd .` moves to the current directory (the '.' represents the current directory)
* `pwd` - "print working directory"
  * Prints out the current directory, if you’re not sure where you are.
  * Helpful to see where you current are in your file system.
* `ls` - "list"
  * Lists the files in the current directory
  * `ls -l` - "long" listing for more information on the files
  * `ls -a` - list all files (including hidden files)
* `mkdir` - "make directory"
  * Creates a new directory in the current directory.
  * `mkdir 6.178` will create a directory called 6.178 in the current directory you're in
* _up arrow_
  * Puts the command you previously ran into your command-line.
  * You can navigate through your history of commands with the _up arrow_ and _down arrow_.

## Git
As we said in the previous lecture, __Git__ is a version control system which means it can __track file changes__. _Why should you care?_ Many software engineers use Git to collaborate on projects. Software engineers use Git to work on code on their own local computer while keeping a working/live version of the code on the remote repo. Git is awesome because if you somehow make some mistake, Git can help you recover your files.

Before we get into the details, there are many resources online to learn more about Git and master it. 6.031 (previously known as 6.005) has great documentation about Git workflow and what Git is [here](http://web.mit.edu/6.005/www/fa16/getting-started/#git). You can also checkout [Gitstream](https://gitstream.csail.mit.edu/), a webapp that helps you learn more about Git made by MIT CSAIL.

### Cloning a Repo

A repository or __repo__ is the folder that stores all these files and keeps track of any file changes. For example, this lecture is a repo. You can __clone__ this repo, to get its contents on your own computer. Cloning is basically taking the contents of this remote (not on your computer) repo and copying/pasting them into a local directory on your own computer. To do this, run this line in command-line:

`git clone URI-of-remote-repo`

* To clone this repo, run: `git clone git@github.mit.edu:6178-2017/lec2.git`
  * If that didn't work, try `git clone https://github.mit.edu/6178-2017/lec2.git`.
  * You should not need to put in your username or password if you setup your SSH keys correctly.
  * If you did not set up your SSH keys, refer to [Lecture 1](https://github.mit.edu/6178-2017/lec1#ssh-keys).

### Important Git Concepts

Here are some important Git concepts to know before we delve into why we do this:
* __commit__
  * A commit is a snapshot of the files in a repo at a given time. Usually you can identify a commit with some hash.
* __add__
  * Before changes to files can be committed, the files must be added or staged.
  * This allows you to pick and choose which changed files should be committed to the remote repo.
* __push__
  * Pushing sends your local commits to the remote repository.
  * You must add, commit, and then push changes in order send these changes to the remote repository. If not, no one else will be able to see your local changes.
* __pull__
  *  Pulling retrieves commits made to the remote repository that are not in your local repository and writes them into your local repository.

### Git Workflow

Conceptually, we use Git to work on things on our local computer to later push them to a remote repo (for example on Github) so that other people can see our code and collaborate with us! There are a couple of Git commands we can run in the command-line that helps us get our changes from our local repo on our computer to the remote repo hosted on Github.

Here is the typical __Git workflow__ or process of which you add your changes, commit your changes, and push them to the remote repository.

#### 0. Pull before making changes.
Sometimes you will make changes in your local repository and they will conflict with changes that have happened in the remote repo. To avoid these conflicts, __always pull before you start making changes to code in a repo.__ This will likely not happen in 6.178 because you will never have to collaborate with someone on the same repo.

To pull potential commits from the remote repo, run the command in the directory of the repo:

```
git pull
```

#### 1. You've made changes to some files. Now what?
Now, you can open up your Text Editor of choice and make as many changes to files as you want. These are all local changes and will not be shown in the remote repository. These changes are only local to your local repository.

#### 2. Checking your repo's status
To see what changes were made since your previous commit, run:

```
git status
```

If no changes were made to files, your screen might look like the one on the left. If changes were made, your screen might look like the one on the right.

No changes             |  Some changes
:---------------------:|:---------------------:
![](./images/gitstatus1.png)  |  ![](./images/gitstatus2.png)

It's good to run `git status` whenever you're not sure if you've made changes since your previous commit. As you can see, Git tells you which file changes are _not staged (or added) for commit_ and which files are _untracked_. All of these file names are in red to grab your attention, but don't worry!

When a file is listed as not staged for commit, that means that the file has changes that need to be added before commiting and pushing. When a file is listed as untracked, that means this is a new file in the repo that Git hasn't seen before, and you will also need to add it before committing and pushing.

#### 3. Adding your file changes
You must stage or add your changes before committing them and pushing them to the remote repo. I like to conceptualize this as: if you have changes that you've made, they must be put on a "stage" before Git can commit them. Anything on this "stage" will be ready for the next commit. Anything not on that stage will not be ready for the next commit.

To add files, run this command:

```
git add <filename>
```

For example, in the screenshot below, I'm running `git add jennifer.txt` to add just `jennifer.txt` file to the "stage". When I run `git status` to see the status of my repo, Git tells me in green, "Hey! I see this file you just added called `jennifer.txt`." `jennifer.txt` shows up under "Changes to be committed" while `README.md` (which was not added) still shows up in red under "Changes not staged for commit".

<img src="./images/gitadd1.png">

You can also add a whole directory of file changes by running:

```
git add <directoryname>
```

Therefore, you can add all changes that you've made to files in the current directory you are in by running `git add .`. Below, I'm running `git add .` which adds both `jennifer.txt` and `README.md` to the "stage". Therefore, both files show up in green under "Changes to be committed".

<img src="./images/gitadd2.png">

#### 4. Committing your staged file changes
Now that you have added the changed files you wanted onto the "stage", we can tell Git to grab all the changes on this "stage" and commit them. Again, I like to conceptualize this as: we have changes on this "stage," and Git can wrap these changes up into a single __commit__ which also is labeled with a __message__.

To commit all your staged file changes, type the command:

```git commit -m <message>```

The `-m` means that we're going to attach a message with this commit. Replace `<message>` with whatever you want. You should detail what you added in this message. For example, below I ran the command `git commit -m "Adding instructions on Git workflow for lecture 2."`. This made all the staged changes into a local commit and cleared the "stage".

<img src="./images/gitcommit1.png">

Note: We made one commit for these changes, but remember that you can make several commits before you push.

#### 5. Making sure your commit shows up in the log
This is an optional step, but you can also make sure your local commits are actually there before you push your local commits to the remote repo. To do this, run:

```
git log
```
As you can see from this screenshot below, running `git log` will show you all the commits in your local repo. You can try to find the commit that you just made in this log by finding your message.

Below, I ran `git log` and I'm trying to find the commit I just made that had the message `"Adding instructions on Git workflow for lecture 2."`. The red rectangle is boxed around this commit. Notice that the commit has an id or has. For instance, my commit's hash is 4ed01bee900b4d0cfd5700357f0b00cf726848f3. This is useful if you want to go back to a previous version of your repo. We won't cover how to go back to a previous commit in history, but you can look this up on your own or ask us in Office Hours/Piazza.

<img src="./images/gitlog.png">

#### 6. Pushing your commits 
You're almost there! Now that we've made changes, added those changes, and committed the changes, all we have to do is push the commits.

To push your commits, run the command:

```
git push origin master
```

The `origin` in the command specifies that you’re pushing to the `origin` remote. By convention, that’s the remote repository you cloned from. The `master` refers to the `master` branch. We won’t use branches in 6.178. `master` is Git’s default branch name, so all our commits will be on master, and that’s the branch we want to push.

Below is an example of pushing commits to the remote repo:
<img src="./images/gitpush.png">

If the push succeeded, you should see a line like:
```
8cce30d..2da8385  master -> master
```

### Exercise 0: Create your own `lec2-<kerberos>` repo.
__TODO:__ Create a repo in the [6178-2017 organization](https://github.mit.edu/6178-2017) by clicking on "New Repository". Name your repository `lec2-<kerberos>` where `<kerberos>` is your kerberos or username to your Github MIT Enterprise account. Make this repo __private__. Don't checkmark the box that will initialize the repo with a README.

Clone __this__ repo (https://github.mit.edu/6178-2017/lec2). Then go into your clone of lec2 repo with `cd lec2`. Then enter the command:
```
git push git@github.mit.edu:6178-2017/lec2-<kerberos>.git
```
This will push this repo's code onto your newly created personal `lec2-<kerberos>` repo. You'll be working off that repo for the rest of this class, so go ahead and clone your new personal repo:
```
git clone git@github.mit.edu:6178-2017/lec2-jjz.git 
```
In the future, this is how we're going to check if you've done your in-lecture exercises! We'll remind you how to do this process in future lectures.

*Remember if the SSH version of cloning is not working for you for some reason, try the HTTPS version and vice versa.

## Methods
Methods are similar to functions in Python. Let's look at this example below.

```
public static double fahrenheitToCelsius(double tempFahrenheit) {
  double tempCelsius;
  tempCelsius = (tempFahrenheit - 32.0) * 5.0 / 9.0;
  return tempCelsius;
}
```
A method is composed of 6 parts (in order): the modifiers, return type, method name, parameters, an exception list (we'll discuss this in a later lecture), and the method body.

For the above example method:

* __modifier__ - `public`, `static`
  * `public` means the method is visible to the "world" (not just visible to the class itself).
  * `static` means the method exists independently of any instance of the class. Usually only uses variables given as parameters and not instance variables.
  * You'll learn more about these modifiers in a future lecture about classes.
* __return type__ - `double`
  * This means that this method returns a variable of type `double`.
* __method name__ - `fahrenheitToCelsius`
  * The name of the method must be unique to the class to prevent __overloading__.
  * __Overloading__ means that two methods have the same method name but different parameters. You can read more about them [here](https://docs.oracle.com/javase/tutorial/java/javaOO/methods.html)
* __parameters__ - `(double temperatureFahrenheit)`
  * This method only has one parameter, but you can have as many parameters as you want in methods.
  * parameters must be declared as `<parameterType> <parameterName>`
* __exception list__ - N/A
  * This method doesn't have an exception list so don't worry about this. We'll discuss exceptions in a future lecture.
* __method body__ - anything in between the curly braces
  * Any code between the curly braces of the method will be executed if the method is called. 

To call this method, just write the method name and provide the parameters necessary. Here we're storing the result in a `double` named `tempCelsius`:
```
double tempCelsius = fahrenheitToCelsius(32.0); // should be 0.0
```

Now with our knowledge of methods and how they are written, how is the special `main` function written?

```
public static void main(String[] args) {
}
```
* __modifier__ - `public`, `static`
* __return type__ - `void`
* __method name__ - `main`
* __parameters__ - `(String[] args)`

### Exercise 1: Write the `estimateRidePrice` method.
__TODO:__ In your personal `lec2-<kerberos>` repo, modify lines 20 & 21 in `Main.java` where we assign `uberPrice` and `lyftPrice` so that we abstract this calculation away into a method. To do this, create a method called `estimateRidePrice`. Allow this method to take all the parameters necessary to calculate the estimated price for an Uber and for a Lyft.

## Specifications
We won't go into depth with specifications, but you can read more about them [here](http://web.mit.edu/6.005/www/fa16/classes/06-specifications/specs/). __Specifications (or specs)__ is a set of comments before a method that tells people all they need to know about the method. Specs are important for software engineers because they allow us to read about what a method does _without actually reading the code_. Specs should have enough information for anyone to understand what the method does and should give enough wiggle room for an engineer to implement the method.

Specs solidify inputs and outputs to a method. If inputs to the method satisfy the requirements outlined in the specs, then the method will guarantee an output that satisfies the requirements outlined in the specs.

Below is an example of a spec for the same `fahrenheitToCelsius` method.

```
/**
* Converts the temperature in Fahrenheit to the temperature in Celsius
* @param tempFahrenheit the temperature in Fahrenheit
* @return the temperature in Celsius
*/
public static double fahrenheitToCelsius(double tempFahrenheit) {
  double tempCelsius;
  tempCelsius = (tempFahrenheit - 32.0) * 5.0 / 9.0;
  return tempCelsius;
}
```
The first line of the spec details what the method does in plain English. `@param` tells us the name of the parameter and what it is in plain English. `@return` tells us what the method will return in plain English.

### Exercise 2: Add specs to your `estimateRidePrice` method.
__TODO:__ Write some specs for your `estimateRidePrice` method. Be sure to write out all the parts of the spec.

## Switch Statements
Switch statements are similar to if statements but is a shorter form. There is a special `default` case after all the other cases. Each case has to end with a `break;` statement.

```
String monthString;
switch (month) {
  case 1:
    monthString = "January";
    break;
  case 2:
    monthString = "February";
    break;
  // More case statements for the rest of the month...
  default:
    monthString = "Invalid Month";
    break;
}
```
## Enums
Enum is a special data type that enables for a variable to be a set of predefined constants. The variable must be equal to one of the values that have been predefined for it. Internally the class represents the different constants as integers.

Below is an example of an enum Direction:
```
public enum Direction {
    NORTH, EAST, SOUTH, WEST
}
```

To declare a variable as the enum Direction:
```
Direction direction = Direction.NORTH;
```

### Exercise 3: Create the `Day` Enum
__TODO__: In your personal `lec2-<kerberos>` repo, create an enum called `Day`. A variable of type `Day` can be any of the days of the week.

## Strings

To create a String literal:
```
String name = "Jennifer";
```
Here are some helpful methods that come with `String`:
```
String firstName = "Jennifer";

// Get the length of a String
int len = firstName.length(); // should be 8

// Get the character at a specific index (0-indexed) of the String
char secondLetter = firstName.charAt(1); // should be 'e'

// Concatenating two strings
String lastName = "Zhang";
String fullName = firstName.concat(lastName);
```
You can read more about Strings [here](https://docs.oracle.com/javase/tutorial/java/data/strings.html)

## Arrays
You can create 1d or 2d arrays:
```
String[] months; // 1d array
int[][] coordinates; // 2d array
```

You can't dynamically allocate space for arrays. Therefore, you have to declare an array's size at assignment:
```
String[] months; // 1d array
months = new String[12];
```

You can index into an array as so:
```
char firstNameArray = {'J', 'e', 'n', 'n', 'i', 'f', 'e', 'r'};
char firstInitial = firstNameArray[0]; // should be 'J'
```
You can read more about arrays [here](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html). In a later lecture we'll learn more about more useful data types other than arrays that are very similar to arrays.

### Exercise 4: Create a `months` array and populate it.
__TODO__: Create an array of type `String` called `months` of size 12. Populate each element of the array with the name of the month as a `String`.

## Final Exercise
__TODO__: Push all your work for today's lecture exercises to your personal lec2 repo (`https://github.mit.edu/6178-2017/lec2-<kerberos>`).
Review how to do so in the [Git Workflow section](https://github.mit.edu/6178-2017/lec2#git-workflow). In short, follow these steps:
```
git add .
git commit -m <some message here>
git push origin master
```
