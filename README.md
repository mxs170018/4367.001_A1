# 4367.001_A1
MXS170018

Part 1: 
Deliverables: DominatorFinder.Java 

In order to ensure that the "doAnalysis" method worked correctly I did a few things:

first and foremost I revised the Soot Source code on github, suprisingly, the entire class is almost identical. 
I found it under soot.toolkits.graph.DominatorsFinder 
The doAnalysis() method is word for word identical. Although, there is always a (VERY VERY SMALL) chance that this is wrong, but i had to check anyways.

second, I ran testSoot.Java (provided in project documentation) to gather all of the statement nodes from GCD.Java 
Then I manually assigned them node numbers and dominators to check against what the program is outputting 

testSoot.Java output 

1: i0 := @parameter0: int 
2: i1 := @parameter1: int
3: if i0 <= i1 goto i8 = i0
4: i7 = i1
5: goto [?= (branch)]
6: $i5 = i0 % i7
7: if $i5 != 0 goto i7 = i7 + -1
8: $i6 = i1 % i7
9: if $i6 != 0 goto i7 = i7 + -1
10: return i7
11: i7 = i7 + -1
12: if i7 >= 1 goto $i5 = i0 % i7
13: goto [?= return 1]
14: i8 = i0
15: goto [?= (branch)]
16: $i3 = i0 % i8
17: if $i3 != 0 goto i8 = i8 + -1
18: $i4 = i1 % i8
19: if $i4 != 0 goto i8 = i8 + -1
20: return i8
21: i8 = i8 + -1
22: if i8 >= 1 goto $i3 = i0 % i8
23: return 1



