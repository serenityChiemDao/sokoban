
JAVA = java
JAVAFLAGS = -cp "../graphics.jar":
MAINCLASS = be.ac.ulg.montefiore.oop.Main
BOARD = ../zero.txt

CLASSFILES = $(patsubst src/%.java, bin/%.class , $(JAVAFILES))
JAVAC = javac
JAVACFLAGS = -Xlint -cp graphics.jar -d bin
JAVAFILES = src/be/ac/ulg/montefiore/oop/Main.java src/be/ac/ulg/montefiore/oop/*/*.java src/be/ac/ulg/montefiore/oop/sokoban/exceptions/*.java

ZIP = zip
FILES = Makefile graphics.jar bin/ -r src/ -x src/*/.*.swp 

all: $(MAINCLASS)

$(MAINCLASS):
	$(JAVAC) $(JAVACFLAGS) $(JAVAFILES) && \
	cd bin && $(JAVA) $(JAVAFLAGS) $(MAINCLASS) $(BOARD)

clean:
	rm -rf bin/* oop_CHIEM_DAO_Davy.zip

archive:
	make clean && $(ZIP) oop_CHIEM_DAO_Davy.zip $(FILES)

