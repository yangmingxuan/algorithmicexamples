package algorithms.struct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/***
 * Design an in-memory file system to simulate the following functions:

    ls: Given a path in string format. If it is a file path, return a list that only contains this file's name. If it is a directory path, return the list of file and directory names in this directory. Your output (file and directory names together) should in lexicographic order.
    
    mkdir: Given a directory path that does not exist, you should make a new directory according to the path. If the middle directories in the path don't exist either, you should create them as well. This function has void return type.
    
    addContentToFile: Given a file path and file content in string format. If the file doesn't exist, you need to create that file containing given content. If the file already exists, you need to append given content to original content. This function has void return type.
    
    readContentFromFile: Given a file path, return its content in string format.
    
     
    
    Example:
    
    Input: 
    ["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
    [[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
    
    Output:
    [null,[],null,null,["a"],"hello"]
    
    Explanation:
    Operation                                         Output                       Explanation
    FileSystem fs = new FileSystem()                   null             The constructor returns nothing
    fs.ls("/")                                          []              Initially, directory Was nothing. So return empty list
    fs.mkdir("/a/b/c")                                 null    Create directory a in directory /.Then create directory b in directory a. Finally create directory c in directory b.
    fs.addContentToFile("/a/b/c/d"."hello")            null    Create a file named d with content "hello" in directory /a/b/c
    fs.ls("/")                                         [a]              Only directory a is in directory /
    fs.readContentFromFile("/a/b/c/d")               "hello"            Output the file content

    
    Note:
    
    You can assume all file or directory paths are absolute paths which begin with / and do not end with / except that the path is just "/".
    You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list a directory or file that does not exist.
    You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the same directory.
 * @author miche
 *
 */
public class FileSystem {

    Dir root;
    public FileSystem() {
        root = new Dir();
    }

    public List<String> ls(String path) {
        List<String> allFiles = new ArrayList<String>();
        if(path.isEmpty()) return allFiles;
        Dir t = root;
        if(!"/".equals(path)) {
            String[] dirpath = path.split("/");
            for(int i = 1; i < dirpath.length - 1; i++) {
                t = t.dirs.get(dirpath[i]);
                if(t == null) {
                    return allFiles;
                }
            }
            //if path is file
            if(t.files.containsKey(dirpath[dirpath.length-1])) {
                allFiles.add(dirpath[dirpath.length-1]);
                return allFiles;
            } else {
                //path is dir
                t = t.dirs.get(dirpath[dirpath.length-1]);
                if(t == null) {
                    return allFiles;
                }
            }
        }
        
        //get all the dirs and fiels in t
        allFiles.addAll(new ArrayList<String>(t.dirs.keySet()));
        allFiles.addAll(new ArrayList<String>(t.files.keySet()));
        
        Collections.sort(allFiles);
        
        return allFiles;
    }
    
    public void mkdir(String path) {
        Dir t = root;
        String[] d = path.split("/");
        for(int i = 1; i < d.length; i++) {
            if(!t.dirs.containsKey(d[i])) {
                t.dirs.put(d[i], new Dir());
            }
            t = t.dirs.get(d[i]);
        }
    }
    
    public void addContentToFile(String filePath, String content) {
        Dir t = root;
        String[] d = filePath.split("/");
        for(int i = 1; i < d.length - 1; i++) {
            if(!t.dirs.containsKey(d[i])) {
                t.dirs.put(d[i], new Dir());
            }
            t = t.dirs.get(d[i]);
        }
        String str = t.files.getOrDefault(d[d.length-1], "");
        t.files.put(d[d.length-1], str+content);
    }
    
    public String readContentFromFile(String filePath) {
        Dir t = root;
        String[] dirpath = filePath.split("/");
        //because note 2
        //You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list a directory or file that does not exist.
        for(int i = 1; i < dirpath.length - 1; i++) {
            t = t.dirs.get(dirpath[i]);
        }
        return t.files.get(dirpath[dirpath.length-1]);
        
        
    }
}
class Dir {
    HashMap<String, Dir> dirs;
    HashMap<String, String> files;
    public Dir() {
        dirs = new HashMap<String, Dir>();
        files = new HashMap<String, String>();
    }
}
