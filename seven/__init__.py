import fileReader
from seven.Directory import Directory

def noSpaceLeftOnDevice(textfile):

    column = fileReader.fileReader(textfile)
    root = Directory('/')
    current_directory = root
    directory_path = root.name
    i = 0
    for cmd in column:
        i += 1
        row = cmd.split()
        print(f'This is the current Row to be checked {row}')
        if row[0] == '$':
            print(f'{i}: command {row[1]} and this current path {directory_path}')
            if row[1] == 'cd':

                if row[2] == '/':
                    current_directory = root
                else:
                    directory_path = update_path(directory_path, row[2])
                    print(f'cd has been changed to {directory_path}')
                    current_directory = retrieve_directory(root, directory_path)
                    print(type(current_directory))
        else:
            if row[0] == 'dir':
                current_directory.add_directory(Directory(row[1]))
                print(f'current directory {current_directory.name} and new directory {Directory(row[1]).name} was added')
            else:
                new_file = {row[1]: int(row[0])}
                current_directory.add_file(new_file)
                print(f'New File {new_file} has been added to {current_directory.name}')


def retrieve_directory(directory: 'Directory', path: str):
    cur_dir = path.split('.', 1)
    if len(cur_dir) == 1:
        print(f'cur_dir ist das {cur_dir}')
        return directory.get_directory(cur_dir[0])
    elif cur_dir[0] == '/':
        print(f'this is the first {cur_dir[0]}')
        return retrieve_directory(directory, cur_dir[1])
    else:
        return retrieve_directory(directory.get_directory(cur_dir[0]), cur_dir[1])

def update_path(current_dir: str, path: str):
    if path == '..':
        test = current_dir.rsplit('.', 1)
        print(test)
        return test[0]
    else:
        test1 = current_dir + '.' + path
        print(test1)
        return test1

def build():
    root = Directory('/')
    root.add_directory(Directory('asdadsasd'))
    next = root.get_directory('asdadsasd')
    print(type(next))
    next.add_directory(Directory('asdasd3'))
    next1 = next.get_directory('asdasd3')
    next1.add_directory(Directory('sdf'))
    next2 = next1.get_directory('sdf')
    next2.add_directory(Directory('sdf'))
    ro = '/'
    ro1 = update_path(ro, 'file1')
    ro2 = update_path(ro1, 'file2')
    ro3 = update_path(ro2, 'file3')
    ro4 = update_path(ro3, '..')
    ro5 = update_path(ro4, 'file4')
    print(ro5)

    print(root)
    test = retrieve_directory(root, '/.asdadsasd.asdasd3.sdf')
    print(f'test {type(test)}')
    print(f'root {root}')

