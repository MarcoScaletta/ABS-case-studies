## Delta-based Verification of Software Families (Case studies)

## Requirements
Java Version: 8

## Structure of the project-folder

This project contains the case studies for three SPLs:
1. `BankAccountSPL/`
2. `MinePumpPL/`
3. `SimplePL/`

The structure of these folders is similar: they contain the ABS code (`ABS/` folder) and the Java+JML code (`JavaAE/` folder). The former is not compiled and it is used as outline for the Java implementation. The latter is a remodelling of the former and each contract can be fully automatically proved by *KeY* using the .jar file `key-2.7-exe.jar`.

Both `ABS/` and `JavaAE/` are structured following the delta-oriented approach with two main folders `core/` and `deltas/`, containing the code for the code and each deltas, respectively.

For both BankAccountSPL and MinePumpPL is also available the original source code in folder `/Original`.

The files of the product-based approach for a subset (only method `timeShift` is considered) of `MinePumpPL` are available in its subfolder `variants-for-evaluation/`, with three subfolders `ABS/` (uncompiled ABS code), `Java/` that contains the code of the product variants, and `cost-and-comparison/` containing the results of the experiments.

## How to reproduce the results
We refer to the absolute path of this folder as `<PATH>/`.

### Loading the proofs
Given a SPL `<SPL-name>` it is necessary open a terminal open `<PATH>/`:

#### Loading files

##### Loading core files
To load the proofs for the core of `<SPL-name>` run the following command in the terminal 
 ```$ java -jar key-2.7-exe.jar <SPL-name>/JavaAE/core/load.key```

*Example*
To load the proofs for the core of `MinePumpPL` run the command:
 ```$ java -jar key-2.7-exe.jar MinePumpPL/JavaAE/core/load.key```

##### Loading deltas files
To load the proofs for delta `<delta-name>` of `<SPL-name>` run the command: 
 ```$ java -jar key-2.7-exe.jar <SPL-name>/JavaAE/deltas/<delta-name>/load.key```

*Example*
To load the proofs for delta `DeltaHighWaterSensor` of `MinePumpPL` run the command:
 ```$ java -jar key-2.7-exe.jar MinePumpPL/JavaAE/deltas/DeltaHighWaterSensor/load.key```

##### Loading product-variants files
To load the proofs for a product variant `<n_feature-selection>` for `MinePumpPL` run the command:
 ```$ java -jar key-2.7-exe.jar MinePumpPL/variants-for-evaluation/Java/<n_feature-selection>/load.key```

*Example*
To load the proofs for the product variant `7_low_high_alarm` for `MinePumpPL` run the command:
 ```$ java -jar key-2.7-exe.jar MinePumpPL/variants-for-evaluation/Java/7_low_high_alarm/load.key```


#### Selecting proofs
After the execution of each of the previous commands the proof manager should show up as in the following picture:
