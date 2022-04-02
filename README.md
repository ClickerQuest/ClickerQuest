# Clicker Quest

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
Clicker Quest is a fun clicker role-playing game! Click as fast as you can, as long as you can to defeat the big bosses! Click to advance to better gear and tougher bosses! Can you save the town from ruin? Click now to play!

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category: Gaming**
- **Mobile: This game is only for mobile devices.**
- **Story: Clicker Quest is an easy-to-pickup idle clicker with a rewarding sense of progression. Users can have some mindless fun while increasing their click power to defeat stronger bosses with more powerful clicks. Users will feel more rewarded the longer they play.**
- **Market: Kids to young adults can enjoy this app. The gameplay is simple yet rewarding so our target market will be excited to keep on playing. They would be able to play anywhere since the core gameplay is simple and on mobile devices.**
- **Habit: A user would use this app in multiple short sessions throughout the day. Even a few minutes on the app can contribute to the rewarding sense of progression, thus users may be inclined to play throughout the day. This app may become very addicting so we will provide a warning to users to stay on the app too long.**
- **Scope: We want to build a playable version of our app. However, we would still be able to show off the core functionalities like gameplay, upgrade systems, etc.**

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

- [X] Home screen displays all necessary assets (gold, atk power, etc)
- [X] Bottom bar navigation to home screen and upgrade screen
- [x] Monster assets are shown on the home screen (image, name, health)
- [ ] User can click on creature to damage it
- [ ] User can earn coins every time he attacks a creature
- [ ] Coins will generate while in the app and off the app
- [ ] User can spend coins in the upgrade screen to increase attack power and other stats
- [ ] Defeating an enemy will spawn a new enemy with more health 
- [ ] User can use the bottom navigation bar to navigate to other screen(s) (upgrade screen, shop screen)

**Optional Nice-to-have Stories**

- [ ] Shop feature where users can use real money to purchase currency
- [ ] User can sign up and login to save their game data across devices
- [ ] Add animations to creatures when they are attacked

**Video Walkthrough
Here's a walkthrough of implemented user stories for Sprint 1:
![ezgif com-gif-maker (3)](https://user-images.githubusercontent.com/81489476/160213514-17c9e8f1-2e39-4e2c-97ad-c4a9e19b2c6f.gif)

### 2. Screen Archetypes

* Stream screen (home/game screen)
   * User can click on creature to damage it
   * User can earn coins evry time he attacks a creature
   * Coins will generate while in and off the app
   * Defeating an enemy will spawn a new enemy with more health
   * User can use the bottom navigation bar to navigate to other screen(s)
* Details screen (upgrade screen)
   * User can spend coins in the upgrade screen to increase attack power and other stats

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Stream screen (home/game screen)
* Details screen (upgrade screen)

**Flow Navigation** (Screen to Screen)

* Stream screen
   * Details screen
* Details screen
   * Stream screen

## Wireframes
[Add picture of your hand sketched wireframes in this section]
![Wireframe](https://user-images.githubusercontent.com/81489476/159105642-2c923f4e-e7f5-4ac5-a9e2-0c9cc331472b.png)


### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
### Models
Monster
| Property | Type    | Description |
| -------- | -------- | -------- |
| monsterName | String    | name of current monster     |
| monsterHealth | Number    | current monster health     |
| monsterImage | File    | image of current monster     |
| stageNumber | String    | stage number as a string     |

Player
| Property | Type    | Description |
| -------- | -------- | -------- |
| attackPower | Number    | amount of damage dealt per click   |
| goldCount | Number    | number of gold user currently has    |
| gemsCounter | Number    | number of gems user currently has     |
| playerUpgrades | String/Number | name of upgrade and how many tiers into the upgrade a player is     |
| playerSkills | String/Number | name of skills chosen and how many tiers into the skill a player is     |
### Networking
- [Add list of network requests by screen ]
#### List of network requests by screen
   - Home Screen
      - (Read/GET) Retrieve monster name, health, image, and stage number
      - (GET/Update) Update the monster's health by the player's attack power whenever player attacks
      - (GET/Update) Update player's gold count after every attack 
   - Upgrades Screen
      - (Read/GET) Retrieve all upgrades
   - Skills Tree Screen
      - (Read/GET) Retrieve all skills
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
