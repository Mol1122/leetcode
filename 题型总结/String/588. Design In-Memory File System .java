/* Design a data structure that simulates an in-memory file system.

Implement the FileSystem class:

FileSystem() Initializes the object of the system.
List<String> ls(String path)
If path is a file path, returns a list that only contains this file's name.
If path is a directory path, returns the list of file and directory names in this directory.
The answer should in lexicographic order.
void mkdir(String path) Makes a new directory according to the given path. The given directory path does not exist. If the middle directories in the path do not exist, you should create them as well.
void addContentToFile(String filePath, String content)
If filePath does not exist, creates that file containing given content.
If filePath already exists, appends the given content to original content.
String readContentFromFile(String filePath) Returns the content in the file at filePath.
 

Example 1:


Input
["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
[[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
Output
[null, [], null, null, ["a"], "hello"]

Explanation
FileSystem fileSystem = new FileSystem();
fileSystem.ls("/");                         // return []
fileSystem.mkdir("/a/b/c");
fileSystem.addContentToFile("/a/b/c/d", "hello");
fileSystem.ls("/");                         // return ["a"]
fileSystem.readContentFromFile("/a/b/c/d"); // return "hello" */

class FileSystem {
    FileNode root;

    public FileSystem() {
        root = new FileNode("");
    }
    
    public List<String> ls(String path) {
        return findNode(path).getList();
    }
    
    public void mkdir(String path) {
        findNode(path);
    }
    
    public void addContentToFile(String filePath, String content) {
        findNode(filePath).addContent(content);
    }
    
    public String readContentFromFile(String filePath) {
        return findNode(filePath).getContent();
    }

    // return the first file (not directory) Node
    private FileNode findNode(String path) {
        String[] files = path.split("/");

        FileNode curr = root;
        for (String file : files) {
            if (file.length() == 0) {
                continue;
            }
            curr.children.putIfAbsent(file, new FileNode(file));
            curr = curr.children.get(file);

            if (curr.isFile()) {
                break;
            }
        }
        return curr;
    }
}

class FileNode {
    TreeMap<String, FileNode> children;
    StringBuilder content;
    String name;

    public FileNode(String name) {
        children = new TreeMap<>();
        content = new StringBuilder();
        this.name = name;
    }

    public String getContent() {
        return content.toString();
    }

    public String getName() {
        return name;
    }

    public void addContent(String content) {
        this.content.append(content);
    }
    
    public boolean isFile() {
        return content.length() > 0;
    }

    public List<String> getList() {
        List<String> list = new ArrayList<>();
        if (isFile()) {
            list.add(getName());
        } else {
            list.addAll(children.keySet());
        }
        return list;
    }
}
//很像字典树