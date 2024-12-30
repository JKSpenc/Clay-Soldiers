import os
import json

dyes = {
    "black": "black_dye",
    "blue": "blue_dye",
    "brown": "brown_dye",
    "cyan": "cyan_dye",
    "default": "water_bucket",
    "gray": "gray_dye",
    "green": "green_dye",
    "lightblue": "light_blue_dye",
    "lime": "lime_dye",
    "magenta": "magenta_dye",
    "pink": "pink_dye",
    "purple": "purple_dye",
    "red": "red_dye",
    "white": "white_dye",
    "yellow": "yellow_dye"
    }

# folder_name = input("folder?")
# os.mkdir(folder_name)
# soldier = input("soldier?")
# dye_name = "minecraft:" + input("dye?")

for folder_name, dye in dyes.items():
    dye_name = "minecraft:" + dye
    os.mkdir(folder_name)
    for i in range(1,9):
        ingredients = ["#clay:soldiers" for i2 in range(i)] + [dye_name]
        recipe_dict = {
            "type": "minecraft:crafting_shapeless",
            "ingredients": ingredients,
            "result": {
              "count": i,
              "id": "clay:soldier/" + (folder_name if folder_name != "default" else "clay")
            }
        }
        with open(folder_name + "/" + str(i) + ".json", "w") as f:
            json.dump(recipe_dict, f, indent=4)
