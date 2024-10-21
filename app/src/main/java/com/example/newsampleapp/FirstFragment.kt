package com.example.newsampleapp

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsampleapp.databinding.FragmentFirstBinding

import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

import android.widget.Toast


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textviewFirst.text = "Welcome to the First Fragment! This is the default landing page."

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.buttonSaveImage.setOnClickListener {
            saveImage()
        }

//        loadImage()

//        // Assuming you have a button to trigger saving the image
//        binding.buttonFirst.setOnClickListener {
//            // Save an image from resources (for demonstration purposes)
//            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.sample_image)
//            saveImageToInternalStorage(requireContext(), "artist_profile", bitmap)
//
//            // Load and display the saved image
//            val savedImage = loadImageFromInternalStorage(requireContext(), "artist_profile")
//            savedImage?.let {
//                binding.imageView.setImageBitmap(it) // Show the saved image in an ImageView
//            }
//        }
    }

    // Save a Bitmap image to internal storage
//    fun saveImageToInternalStorage(context: Context, imageName: String, bitmap: Bitmap): String {
//        val directory = context.filesDir // Access internal storage directory
//        val imageFile = File(directory, "$imageName.png")
//
//        var fos: FileOutputStream? = null
//        try {
//            fos = FileOutputStream(imageFile)
//            // Compress the bitmap and save it as a PNG image
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
//        } catch (e: IOException) {
//            e.printStackTrace()
//        } finally {
//            try {
//                fos?.close()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//        }
//
//        // Return the file path of the saved image
//        return imageFile.absolutePath
//    }
//
//    // Load an image from internal storage
//    fun loadImageFromInternalStorage(context: Context, imageName: String): Bitmap? {
//        val directory = context.filesDir
//        val imageFile = File(directory, "$imageName.png")
//
//        return if (imageFile.exists()) {
//            BitmapFactory.decodeFile(imageFile.absolutePath)
//        } else {
//            null // Handle case where image file doesn't exist
//        }
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveImage() {
        // For demonstration, we'll use a placeholder drawable image from the resources
        val drawable = resources.getDrawable(R.drawable.sample_image, null)
        val bitmap = (drawable as BitmapDrawable).bitmap

        val filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
        val fileName = "saved_image.png"
        val file = File(filePath, fileName)

        try {
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()

            Toast.makeText(context, "Image saved: $filePath/$fileName", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context, "Error saving image", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to load and display the saved image
    private fun loadImage() {
        val filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
        val fileName = "saved_image.png"
        val file = File(filePath, fileName)

        if (file.exists()) {
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            binding.imageView.setImageBitmap(bitmap)
        } else {
            Toast.makeText(context, "No saved image found", Toast.LENGTH_SHORT).show()
        }
    }

}