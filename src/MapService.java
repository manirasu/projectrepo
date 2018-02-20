import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by OManimaran on 19-02-2018.
 */
public class MapService {
    private static final String MAPBEAN = "D://mBean.txt";
    private static final String MAPLOC = "D://mapLoc.txt";

    public List<MapInfo> getMapInfo() {
        List<MapInfo> mapInfo = null;
        try {
            Map<String, MapBean> mBeanMap = GetMapBeanInfo();
            Map<String, MapLocation> mLocationMap = GetMapLocation();
            mapInfo = getMapDetails(mBeanMap, mLocationMap);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mapInfo;
    }

    private List<MapInfo> getMapDetails(Map<String, MapBean> mBeanMap, Map<String, MapLocation> mLocationMap) {
        // TODO Auto-generated method stub
        List<MapInfo> mapInfoList = new ArrayList<>();
        for (Map.Entry<String, MapBean> mapValue : mBeanMap.entrySet()) {

            if (mLocationMap.containsKey(mapValue.getKey())) {
                MapInfo mapInfo = new MapInfo();
                mapInfo.setCity(mapValue.getValue().city);

                MapLocation mLoc = mLocationMap.get(mapValue.getKey());
                mapInfo.setLat(mLoc.lat);
                mapInfo.setLang(mLoc.lang);

                mapInfoList.add(mapInfo);
            }
        }

        return mapInfoList;
    }

    private Map<String, MapBean> GetMapBeanInfo() throws IOException {
        Map<String, MapBean> mBeanMap = new HashMap<>();
        File file = new File(MAPBEAN);
        List<String> loc = FileUtils.readLines(file,"UTF-8");

        for (String item : loc) {

            String[] arrLocation = item.split(",");
            MapBean mapBean = new MapBean();
            mapBean.setCity(arrLocation[1]);
            mapBean.setOption1(arrLocation[2]);
            mapBean.setOption2(arrLocation[3]);
            mapBean.setOption3(arrLocation[4]);
            mBeanMap.put(arrLocation[1], mapBean);

        }
        return mBeanMap;

    }

    public Map<String, MapLocation> GetMapLocation()  {
        Map<String, MapLocation> mLocationMap = new HashMap<>();
        File file = new File(MAPLOC);
        List<String> loc = null;
        try {
            loc = FileUtils.readLines(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String item : loc) {
            String[] arrLocation = item.split(",");
            if(arrLocation.length >=3) {
                MapLocation mLocation = new MapLocation();
                mLocation.setCity(arrLocation[0]);
                mLocation.setLang(arrLocation[1]);
                mLocation.setLat(arrLocation[2]);
                mLocationMap.put(arrLocation[0], mLocation);
            }
        }
        return mLocationMap;

    }

    public static void main(String s[]) {
        MapService mapS = new MapService();
        mapS.getMapInfo();
    }

}
