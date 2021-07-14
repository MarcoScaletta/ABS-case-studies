## Delta-based Verification of Software Families (Case studies)

## Requirements
Java Version: 8

## Structure of the project-folder

This project contains the case studies for three SPLs:
1. `BankAccountSPL/`
2. `MinePumpPL/`
3. `SimplePL/`

The structure of these folders is similar: they contain the ABS code (`ABS/` folder) and the Java+JML code (`JavaAE/` folder).
The former is not compiled and it is used as outline for the Java implementation.
The latter is a remodelling of the former and each contract can be fully automatically proved by *KeY* using the .jar file `key-2.7-exe.jar`.

Both `ABS/` and `JavaAE/` are structured following the delta-oriented approach with two main folders `core/` and `deltas/`, containing the code for the code and each deltas, respectively.

## How to reproduce the results
We refer to the absolute path of this folder as `<PATH>/`.

