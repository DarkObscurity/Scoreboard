package eu.rhbict.Scoreboard.manager;

import eu.rhbict.Scoreboard.utill.Utils;

import java.util.List;

/**
 * Created by Rien on 22-6-2017.
 */
public class Line {

    int update = 0;
    int scroll_pos = 0;
    int iterations = 0;
    int entries = 0;

    List<String> lines = null;
    public String current = "";

    public Line(List<String> lines, int update)
    {
        this.update = update;
        this.lines = lines;

        for(String dump : lines)
            entries++;
    }

    public String next()
    {
        iterations++;
        if(iterations >= update)
        {
            iterations = 0;
            scroll_pos++;
        }
        if(scroll_pos >= (entries - 1))
            scroll_pos = 0;

        Utils.debug("Iterations:" + iterations + " update:" + update + " pos:" + scroll_pos + " entries:" + entries);


        return (current = lines.get(scroll_pos));


    }

}
