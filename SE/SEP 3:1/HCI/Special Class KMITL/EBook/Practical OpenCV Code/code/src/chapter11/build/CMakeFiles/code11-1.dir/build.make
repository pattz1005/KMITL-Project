# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 2.8

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list

# Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E remove -f

# The program to use to edit the cache.
CMAKE_EDIT_COMMAND = /usr/bin/ccmake

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/samarth/Documents/book/code/src/chapter11

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/samarth/Documents/book/code/src/chapter11/build

# Include any dependencies generated for this target.
include CMakeFiles/code11-1.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/code11-1.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/code11-1.dir/flags.make

CMakeFiles/code11-1.dir/src/code11-1.cpp.o: CMakeFiles/code11-1.dir/flags.make
CMakeFiles/code11-1.dir/src/code11-1.cpp.o: ../src/code11-1.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /home/samarth/Documents/book/code/src/chapter11/build/CMakeFiles $(CMAKE_PROGRESS_1)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object CMakeFiles/code11-1.dir/src/code11-1.cpp.o"
	/usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/code11-1.dir/src/code11-1.cpp.o -c /home/samarth/Documents/book/code/src/chapter11/src/code11-1.cpp

CMakeFiles/code11-1.dir/src/code11-1.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/code11-1.dir/src/code11-1.cpp.i"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /home/samarth/Documents/book/code/src/chapter11/src/code11-1.cpp > CMakeFiles/code11-1.dir/src/code11-1.cpp.i

CMakeFiles/code11-1.dir/src/code11-1.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/code11-1.dir/src/code11-1.cpp.s"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /home/samarth/Documents/book/code/src/chapter11/src/code11-1.cpp -o CMakeFiles/code11-1.dir/src/code11-1.cpp.s

CMakeFiles/code11-1.dir/src/code11-1.cpp.o.requires:
.PHONY : CMakeFiles/code11-1.dir/src/code11-1.cpp.o.requires

CMakeFiles/code11-1.dir/src/code11-1.cpp.o.provides: CMakeFiles/code11-1.dir/src/code11-1.cpp.o.requires
	$(MAKE) -f CMakeFiles/code11-1.dir/build.make CMakeFiles/code11-1.dir/src/code11-1.cpp.o.provides.build
.PHONY : CMakeFiles/code11-1.dir/src/code11-1.cpp.o.provides

CMakeFiles/code11-1.dir/src/code11-1.cpp.o.provides.build: CMakeFiles/code11-1.dir/src/code11-1.cpp.o

# Object files for target code11-1
code11__1_OBJECTS = \
"CMakeFiles/code11-1.dir/src/code11-1.cpp.o"

# External object files for target code11-1
code11__1_EXTERNAL_OBJECTS =

code11-1: CMakeFiles/code11-1.dir/src/code11-1.cpp.o
code11-1: /home/samarth/libraries/opencv-2.4.6.1/build/lib/libopencv_calib3d.so
code11-1: /home/samarth/libraries/opencv-2.4.6.1/build/lib/libopencv_contrib.so
code11-1: /home/samarth/libraries/opencv-2.4.6.1/build/lib/libopencv_core.so
code11-1: /home/samarth/libraries/opencv-2.4.6.1/build/lib/libopencv_features2d.so
code11-1: /home/samarth/libraries/opencv-2.4.6.1/build/lib/libopencv_flann.so
code11-1: /home/samarth/libraries/opencv-2.4.6.1/build/lib/libopencv_gpu.so
code11-1: /home/samarth/libraries/opencv-2.4.6.1/build/lib/libopencv_highgui.so
code11-1: /home/samarth/libraries/opencv-2.4.6.1/build/lib/libopencv_imgproc.so
code11-1: /home/samarth/libraries/opencv-2.4.6.1/build/lib/libopencv_legacy.so
code11-1: /home/samarth/libraries/opencv-2.4.6.1/build/lib/libopencv_ml.so
code11-1: /home/samarth/libraries/opencv-2.4.6.1/build/lib/libopencv_nonfree.so
code11-1: /home/samarth/libraries/opencv-2.4.6.1/build/lib/libopencv_objdetect.so
code11-1: /home/samarth/libraries/opencv-2.4.6.1/build/lib/libopencv_photo.so
code11-1: /home/samarth/libraries/opencv-2.4.6.1/build/lib/libopencv_stitching.so
code11-1: /home/samarth/libraries/opencv-2.4.6.1/build/lib/libopencv_superres.so
code11-1: /home/samarth/libraries/opencv-2.4.6.1/build/lib/libopencv_ts.so
code11-1: /home/samarth/libraries/opencv-2.4.6.1/build/lib/libopencv_video.so
code11-1: /home/samarth/libraries/opencv-2.4.6.1/build/lib/libopencv_videostab.so
code11-1: /home/samarth/libraries/opencv-2.4.6.1/build/lib/libopencv_world.so
code11-1: CMakeFiles/code11-1.dir/build.make
code11-1: CMakeFiles/code11-1.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --red --bold "Linking CXX executable code11-1"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/code11-1.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/code11-1.dir/build: code11-1
.PHONY : CMakeFiles/code11-1.dir/build

CMakeFiles/code11-1.dir/requires: CMakeFiles/code11-1.dir/src/code11-1.cpp.o.requires
.PHONY : CMakeFiles/code11-1.dir/requires

CMakeFiles/code11-1.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/code11-1.dir/cmake_clean.cmake
.PHONY : CMakeFiles/code11-1.dir/clean

CMakeFiles/code11-1.dir/depend:
	cd /home/samarth/Documents/book/code/src/chapter11/build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/samarth/Documents/book/code/src/chapter11 /home/samarth/Documents/book/code/src/chapter11 /home/samarth/Documents/book/code/src/chapter11/build /home/samarth/Documents/book/code/src/chapter11/build /home/samarth/Documents/book/code/src/chapter11/build/CMakeFiles/code11-1.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/code11-1.dir/depend

