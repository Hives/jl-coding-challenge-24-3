# John Lewis Coding Challenge 24

Texas Hold 'Em
<https://coding-challenges.jl-engineering.net/challenges/challenge-24/>

Using Kotlin

## Thoughts etc.

A lot of repetition in `getPairs`, `getThreeOfAKind`, `getFourOfAKind`. Will need to write a test for the function that can be pulled out though.

In the test for detecting a straight where there are two cards of the same value it requires a specific one of the duplicates to be present. Obviously we shouldn't care which one is included.