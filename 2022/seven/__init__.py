import fileReader
from seven.Directory import Directory


def noSpaceLeftOnDevice(textfile):
    column = fileReader.fileReader(textfile)
    root = Directory('/')
    current_directory = root
    directory_path = root.name
    dir_ar = {}
    for cmd in column:
        row = cmd.split()
        # print(f'This is the current Row to be checked {row}')
        if row[0] == '$':
            # print(f'command {row[1]} and this current path {directory_path}')
            if row[1] == 'cd':
                if row[2] == '/':
                    current_directory = root
                else:
                    directory_path = update_path(directory_path, row[2], dir_ar)
                    print(dir_ar)
                    # print(f'cd has been changed to {directory_path}')
                    current_directory = retrieve_directory(root, directory_path)
        else:
            if row[0] == 'dir':
                current_directory.add_directory(Directory(row[1]))
                # print(
                #    f'current directory {current_directory.name} and new directory {Directory(row[1]).name} was added')
            else:
                new_file = {row[1]: int(row[0])}
                current_directory.add_file(new_file)
                # print(f'current_directory {current_directory.files.values()}')
                # print(f'current_directory {sum(current_directory.files.values())}')
                # print(f'New File {new_file} has been added to {current_directory.name}')
    result = {}
    calculate_size(root, result)
    print(result)
    print(f'Result: {sum(result.values())}')


def calculate_size(directory: 'Directory', dire: dict):
    result = 0
    if len(directory.directories) > 0:
        for d in directory.directories:
            result += calculate_size(d, dire)

    check = result + sum(directory.files.values())
    if 100000 >= check > 0:
        print(f'this directory has less than 100.000 {directory.name} with space: {check}')
        dire.update({directory.name: check})
    return check


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


def update_path(current_dir: str, path: str, path_ar: dict):
    if path == '..':
        return current_dir.rsplit('.', 1)[0]
    else:
        t = {len(path_ar) + 1: current_dir + '.' + path}
        path_ar.update(t)
        return current_dir + '.' + path
