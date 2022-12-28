class Directory:
    def __init__(self, name: str):
        self.name = name
        self.files = {}
        self.directories = []

    def add_file(self, file: dict):
        self.files.update(file)

    def add_directory(self, directory: 'Directory'):
        self.directories.append(directory)

    def get_something(self, directory: str):
        return next(filter(lambda dire: dire.name == directory, self.directories))

    def get_directory(self, dires: str):
        for dire in self.directories:
            print(type(dire))
            print(dire.name)
            if dire.name == dires:
                return dire
