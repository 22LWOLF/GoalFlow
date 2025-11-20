## Goal Flow – Final Project Proposal
Team Members
- Chanse
- Luke
- August
- Kylie

## Date
9/26/2025

## App Name
Goal Flow

## Problem / Issue Addressed
Many people begin personal development journeys with enthusiasm but lose motivation over time. Goal Flow helps users maintain consistency by tracking goals, building healthy streaks, and providing encouragement. The app offers reminders, streak tracking, and progress visualization to keep users accountable and motivated.

## Types of Goals Tracked
Users will be able to create and track:
- Daily habits (exercising, reading, ect.).
- Weekly goals (cooking at home 3 times per week, finishing homework by a certain date).
- Long-term goals (running a 5K, completing a course, building a new habit).

## How Progress is Tracked
- Each goal created has a check option where users mark a goal as complete for the day/week.
- The tracker activity records whether a goal was completed and updates the users streak length.

## Progress visualization includes:
- A daily and weekly calendar view.
- Streak counters ("5 days in a row”).
- Badges for milestones (“7-Day Streak” or a “30-Day Streak”).

## Planned Activities and Purpose
- Login Activity (Chanse) – Authenticates users and stores their username and password.
- Dashboard Activity (Kylie) – Central hub showing streaks, goals, and navigation.
- Goal Creation Activity (Luke) – Create or edit goals, set frequency, set possible reminders.
- Tracker Activity (August) – Displays detailed progress and allows users to check off completed goals. Updates streaks.
- Achievements Activity (All) – Shows badges and milestones earned.
- Settings Activity (Chanse) – Adjust reminders, notifications, and profile.

- Flow: Login -> Dashboard -> (Goals / Tracker / Achievements / Settings) -> Dashboard.

## Rough Sketches of Activities
- Login Screen
- Username and Password fields
  * currently admin 1234
- Login button -> Dashboard

## Dashboard
- Buttons: Create Goal, Tracker, Achievements, Settings
- Overview of current streaks and goals

## Goal Creation
- Fields: Goal title, description, frequency (daily or weekly or long-term)
- Reminder toggle
- Save button -> returns to Dashboard

## Tracker
- List of goals with a checkbox for each
- Progress bar or streak counter updates when checked
- Back button -> Dashboard

## Achievements
- List of badges earned
- Back button -> Dashboard

## Settings
- Switches for notifications and reminders
- Edit profile option
- Back button -> Dashboard

## Data to Persist
- User profile info. 
- List of goals. 
- Streak progress and completion history. 
- Achievements and badges. 
- Settings and preferences. 

## Data to Share Across Users
- Leaderboard for longest streaks.
- Community blog board.
- Community challenges.

## Risky Components
# Uses an outside API
- Outside APIs: notifications, sign-in
# Requires functionality we will not talk about
- Community features: blog and challenges require more advanced handling.
# Requires functionality we will talk about later
- Later topics: push notifications, media handling, advanced DB services.

## Plan for persistent data:
# Data
The data that will eventually need to be stored in the app includes information about users, their goals, and their app settings. For each user the app will store their username, password, and email address to identify and allow the user to login. The app will also store what the user’s goals are, their goal streaks, and the progress to see how close they are to their goal. The app will also try and store personal settings like notifications on or off. Every goal will have some sort of id number and the same for the user. 

# Persistence Plan
We plan to use SharedPreferences and JSON storage. We think that this approach will be the best suited for our early versions of the app because the data that needs persisting is not very complex, but because of the limitations of SharedPreferences will we turn our data into JSON files to get around the SharedPreferences limitations. This method will be best for our current application because it is far simpiler to implement then a room. This also includes using GSON to un-JSON-ify our data.

