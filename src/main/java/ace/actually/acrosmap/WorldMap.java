package ace.actually.acrosmap;

import org.apache.logging.log4j.core.util.FileUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WorldMap {
    public HashMap<String,int[][]> map;

    public WorldMap()
    {
        map=new HashMap<>();

    }

    public void in(int x, int y, int id)
    {
        int nx = x/100;
        int ny = y/100;
        String k = (nx*100)+","+(ny*100);
        int[][] page;
        if (map.containsKey(k))
        {
            page=map.get(k);
        }
        else
        {
            page = new int[100][100];
        }
        if(x<0)
        {
            x=100+x;
        }
        if(y<0)
        {
            y=100+y;
        }
        page[(x%100)][(y%100)]=id;
        map.put(k,page);
    }

    public void save()
    {
        List<String> lines = new ArrayList<>();
        for(String key: map.keySet())
        {
            lines.add(key);
            lines.add(Arrays.deepToString(map.get(key)));
        }
        try {
            FileWriter writer = new FileWriter("map.txt");
            for(String in: lines)
            {
                writer.write(in+System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
