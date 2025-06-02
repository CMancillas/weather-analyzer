# Variables
JAVAC = javac
JAVA = java
SRC = DataReader.java Analyzer.java WeatherData.java
MAIN = Analyzer.java

# Targets
all: compile run 

compile:
	$(JAVAC) $(SRC)

run:
	$(JAVA) $(MAIN)

clean:
	rm -f *.class