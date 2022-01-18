This project is a proposed solution to the JetBrains Academy project named "Simple Search Engine".

Below is described the design choices made in the proposed solution:

- The application works on data provided in a file. The path of this file is specified in the command line parameter ```--data``` in the format of text lines with space in between words. 
As example: ```--data text.txt```


- The application menu is constructed using the Strategy Pattern. By using this, additional menu options can be added with less hasle and less risk, as no changes are needed to existing menu options or methods to handle them.
To add a new menu option, add a new class that implements the MenuOperation interface, and add it to the menu method ```getActions```.



- The search strategies are implemented according to the Template Pattern. The thinking here is, that all applicable strategies goes through the same steps, where some are overridden by each strategy.
As it turns out in this case, there was only the single step.