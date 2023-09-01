runApp: FrontendFD.class
	java --module-path ./javafx-sdk-17.0.7/lib/ --add-modules javafx.controls FrontendFD

runTests:
	make runFrontendDeveloperTests
	make runDataWranglerTests
	make runAlgorithmEngineerTests


runDataWranglerTests:
	javac -cp .:junit5.jar DataWranglerTests.java
	java -jar junit5.jar -cp . --select-class=DataWranglerTests
	javac PackageCenterDW.java
	javac PackageReaderDW.java

runFrontendDeveloperTests: FrontendDeveloperTests.class
	java --module-path ./javafx-sdk-17.0.7/lib/ --add-modules javafx.controls --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED -jar junit5.jar -cp .:JavaFXTester.jar -c FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java FrontendFD.class
	javac --module-path ./javafx-sdk-17.0.7/lib/ --add-modules javafx.controls -cp .:junit5.jar:JavaFXTester.jar FrontendDeveloperTests.java FrontendFD.java BackendBD.java BackendInterfaceBD.java PackageCenterDW.java PackageCenter.java AEFunctionalGraph.java BaseGraph.java FunctionalGraph.java

FrontendFD.class: FrontendFD.java PackageCenterDW.class BackendBD.class
	javac --module-path ./javafx-sdk-17.0.7/lib/ --add-modules javafx.controls FrontendFD.java BackendBD.java BackendInterfaceBD.java PackageCenterDW.java PackageCenter.java

PackageCenterDW.class: PackageCenterDW.java
	javac PackageCenterDW.java

BackendBD.class: BackendBD.java
	javac BackendBD.java

runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	java --module-path ./javafx-sdk-17.0.7/lib/ --add-modules javafx.controls --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED -jar junit5.jar -cp .:JavaFXTester.jar -c--select-class=AlgorithmEngineerTests

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java BaseGraph.java BaseGraph.class FunctionalGraph.java FunctionalGraph.class GraphADT.java GraphADT.class AEFunctionalGraph.java AEFunctionalGraph.class
	javac --module-path ./javafx-sdk-17.0.7/lib/ --add-modules javafx.controls -cp .:junit5.jar:JavaFXTester.jar AlgorithmEngineerTests.java

BaseGraph.class: BaseGraph.java
	javac BaseGraph.java

FunctionalGraph.class: FunctionalGraph.java
	javac FunctionalGraph.java

GraphADT.class: GraphADT.java
	javac GraphADT.java

AEFunctionalGraph.class: AEFunctionalGraph.java
	javac AEFunctionalGraph.java

clean:
	rm *.class
