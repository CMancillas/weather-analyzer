# Variables
JAVAC = javac
JAVA = java
SRC_DIR = src
BIN_DIR = bin
MAIN_CLASS = Main 
MAIN_FILE = $(SRC_DIR)/main/Main.java

# Collecting all java files in src/
SOURCES = $(shell find $(SRC_DIR) -name "*.java")

# Targets
all: compile run 

compile:
	$(JAVAC) -d $(BIN_DIR) -sourcepath $(SRC_DIR) $(SOURCES)

run:
	$(JAVA) -cp $(BIN_DIR) $(MAIN_CLASS)

clean:
	rm -rf $(BIN_DIR)/
