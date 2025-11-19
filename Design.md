## Card

| Name     | Type        | Access Modifiers | Explain                      | Parameters                 | Return |
| -------- | ----------- | ---------------- | ---------------------------- | -------------------------- | ------ |
| value    | int         | private          | stores the value of the card |                            |        |
| suit     | char        | private          | stores the suit of the card  |                            |        |
| isA      | function    | public           | determine is the card an Ace | void (self.value)          | bool   |
| Card     | constructor | public           |                              | value : int<br>suit : char |        |
| getValue | function    | public           | getter of value              |                            | value  |
| toString | function    | public           | output the card as String    |                            | String |

## Decks

| Name  | Type              | Access Modifiers | Explain                                     | Parameters         | Return |
| ----- | ----------------- | ---------------- | ------------------------------------------- | ------------------ | ------ |
| cards | `ArrayList<Card>` | private          | store the shuffled cards                    |                    |        |
| index | int               | private          | store the index of the next card to be draw |                    |        |
| Decks | constructor       | public           | remember to shuffle the cards               | noOfDeckUsed : int |        |
| draw  | function          | public           | draw card from cards<br>remember index++    |                    |        |
| reset | function          | public           | shuffle cards and set index to 0            |                    |        |

## Player

| Name           | Type              | Access Modifiers | Explain                                                           | Parameters       | Return |
| -------------- | ----------------- | ---------------- | ----------------------------------------------------------------- | ---------------- | ------ |
| hand           | `ArrayList<Card>` | protected        | stores the cards the player has                                   |                  |        |
| value          | int               | protected        | the value of the cards that the player has                        |                  |        |
| accountBalance | int               | protected        | store money                                                       |                  |        |
| decks          | Decks             | protected        | reference to the decks that the player is going to draw card from |                  |        |
| calValue       | function          | protected        | calculate the value the player has. note that                     | void (self.hand) |        |
| Player         | constructor       | public           | get decks for the deck to draw cards from                         | decks : Decks    |        |
| play           | function          | public           | IO for player                                                     |                  |        |
| bet            | function          | public           | let user bet                                                      | int              |        |
| reset          | function          | public           | reset hand and value                                              |                  |        |

## Dealer extend Player
| Name | Type     | Access Modifiers | Explain                   | Parameters | Return |
| ---- | -------- | ---------------- | ------------------------- | ---------- | ------ |
| play | function | public override  | draw new card if value<17 |            |        |

## Blackjack
The single player mode of blackjack

| Name    | Type                | Access Modifiers | Explain                             | Parameters | Return |
| ------- | ------------------- | ---------------- | ----------------------------------- | ---------- | ------ |
| players | `ArrayList<Player>` | public           |                                     |            |        |
| dealer  | Dealer              | public           |                                     |            |        |
| init    | function            | public           | draw 2 cards for dealer and players |            |        |
| play    | function            | public           |                                     |            |        |
| main    | function            | public           | entry point for single player       |            |        |

## Server
The server of the multiplayer mode

| Name    | Type                | Access Modifiers | Explain                             | Parameters | Return |
| ------- | ------------------- | ---------------- | ----------------------------------- | ---------- | ------ |


## Client
The client of the multiplayer mode

| Name    | Type                | Access Modifiers | Explain                             | Parameters | Return |
| ------- | ------------------- | ---------------- | ----------------------------------- | ---------- | ------ |
