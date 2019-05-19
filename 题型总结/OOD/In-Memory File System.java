/* Entry.java
Implement the abstract class Entry */
public abstract class Entry {
  protected Directory parent;
  protected long created;
  protected long lastUpdated;
  protected long lastAccessed;
  protected String name;
  
  public Entry(String name, Directory parent) {
      this.name = name;
      this.parent = parent;;
      //created = System.currentTimeMillis();
  }

  public boolean delete() {
      if (parent == null) {
          return false;
      }
      return parent.deleteEntry(this);
  }

  public abstract int size();

  public String getFullPath() {
      if (parent == null) {
          return name;
      } else {
          return parent.getFullPath() + "/" + name;
      }
  }

  public void changeName(String n) {
      this.name = n;
  }

  public String getName() {
      return name;
  }
  
  public long getLastUpdatedTime() {
      return lastUpdated;
  }
}

/* File.java
Implement the File class. */
public class File extends Entry {
  private String content;
  private int size;
  
  public File(String name, Directory parent, int size) {
      super(name, parent);
      this.size = size;
  }

  public int size() {
      return size;
  }

  public String getContents() {
      return content;
  }

  public void setContents(String c) {
      content = c;
  }
}

/* Directory.java 
Implement the Directory class.*/
public class Directory extends Entry {
  private List<Entry> contents;
  
  public Directory(String name, Directory parent) {
      super(name, parent);
      contents = new ArrayList<>();
  }

  public List<Entry> getContents() {
      return contents;
  }

  public int size() {
      int size = 0;
      for (Entry e : contents) {
          size += e.size();
      }
      return size;
  }

  public int numberOfFiles() {
      int count = 0;
      for (Entry e : contents) {
          if (e instanceof Directory) {
              count++; //directory counts as a file
              Directory d = (Directory)e;
              count += d.numberOfFiles();
          } else if (e instanceof File) {
              count++;
          }
      }
      return count;
  }

  public boolean deleteEntry(Entry entry) {
      return contents.remove(entry);
  }

  public void addEntry(Entry entry) {
      contents.add(entry);
  }
}

/* FileSystem.java
Implement the file system, which allows the operations showed the in the template methods. */
public class FileSystem {
  private final Directory root;
  
  public FileSystem() {
      root = new Directory("/", null);
  }
  // /foo/bar, extries.length = 3 + 1 = 4
  /* 输入的最后一个component是没有children的，所以最后的一个entry是null，如果不是null，
  说明在这个不是null的值不是当前这个path的。 */
  private List<Entry> resolve(String path) {
      assert path.startsWith("/");
      String[] components = path.substring(1).split("/");
      List<Entry> entries = new ArrayList<Entry>(components.length + 1);
      entries.add(root);
    
      Entry entry = root;
      for (String component : components) {
          if (entry == null || !(entry instanceof Directory)) {
              //throw new IllegalArgumentException("invalid path:" + path);
              return new ArrayList<>();
          }
          if (!component.isEmpty()) {
              entry = ((Directory)entry).getChild(component);
              entries.add(entry);
          }
      }
      return entries;
  }
  
  public void mkdir(String path) {
      List<Entry> entries = resolve(path);
      //因为最后一个是null
      if (entries.get(entries.size() - 1) != null) {
          //throw new IllegalArguementException("Directory already exist: " + path);
          return;
      }
      String[] components = path.split("/");
      final String dirName = components[components.length - 1];
      final Directory parent = (Directory)entries.get(entries.size() - 2);
      Directory newDir = new Directory(dirName, parent);
      parent.addEntry(newDir);
  }
  
  public void createFile(String path) {
      assert !path.endsWith("/");
      List<Entry> entries = resolve(path);
      if (entries.get(entries.size() - 1) != null) {
          //throw new IllegalArguementException("File already exists: " + path);
          return;
      }
      final String fileName = path.substring(path.lastIndexOf("/") + 1);
      final Directory parent = (Directory)entries.get(entries.size() - 2);
      File file = new File(fileName, parent, 0);
      parent.addEntry(file);
  }
  
  public void delete(String path) {
      List<Entry> entries = resolve(path);
      if (entries.get(entries.size() - 1) == null) {
          return;
      }
      Entry entry = (Directory)entries.get(entries.size() - 1);
      entry.delete();
  }
  
  public List<Entry> list(String path) { 
      List<Entry> entries = resolve(path);
      entries.remove(entries.size() - 1);
      return entries;
  }
  
  public int count() {
      return root.numberOfFiles();
  }
}